#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
OPENAPI_DIR="$ROOT_DIR/openapi"
OUT_DIR="$ROOT_DIR/docs/api"

command -v python3 >/dev/null 2>&1 || { echo "python3 not found. Install Python 3." >&2; exit 1; }

if [ ! -d "$OPENAPI_DIR" ]; then
  echo "OpenAPI directory $OPENAPI_DIR not found. Run scripts/gen-openapi.sh first." >&2
  exit 1
fi

mapfile -t SWAGGER_FILES < <(find "$OPENAPI_DIR" -name '*.swagger.json' -type f | sort)

if [ "${#SWAGGER_FILES[@]}" -eq 0 ]; then
  echo "No *.swagger.json files found under $OPENAPI_DIR. Generate OpenAPI specs first." >&2
  exit 1
fi

mkdir -p "$OUT_DIR"

convert_file() {
  local input="$1"
  local output="$2"
  mkdir -p "$(dirname "$output")"
  python3 - "$input" "$output" <<'PY'
import json
import sys
from pathlib import Path
from collections import defaultdict

input_path = Path(sys.argv[1])
output_path = Path(sys.argv[2])

with input_path.open() as f:
    data = json.load(f)

lines = []
info = data.get("info", {})
title = info.get("title", "").replace(".proto", "").replace("/", " ").title()
if not title:
    title = input_path.stem.replace(".swagger", "").replace("/", " ").title()
version = info.get("version", "")
description = info.get("description", "")

# Title
lines.append(f"# {title} API Specification")
if description:
    lines.append("")
    lines.append(description)

# Server info
host = data.get("host", "")
base_path = data.get("basePath", "")
schemes = data.get("schemes", ["http"])
if host:
    server_url = f"{schemes[0] if schemes else 'http'}://{host}{base_path}"
    lines.append("")
    lines.append(f"Server: {server_url}")

# Security/Auth info
security_definitions = data.get("securityDefinitions", {})
security = data.get("security", [])
if security_definitions or security:
    lines.append("")
    lines.append("Authentication: hầu hết các endpoint yêu cầu header:")
    for sec_name, sec_def in security_definitions.items():
        sec_type = sec_def.get("type", "")
        if sec_type == "apiKey":
            name = sec_def.get("name", "")
            in_loc = sec_def.get("in", "")
            lines.append(f"- {name}: Bearer <token>")
        elif sec_type == "oauth2":
            lines.append("- Authorization: Bearer <token>")

definitions = data.get("definitions", {})

def resolve_ref(ref, definitions):
    """Resolve $ref to actual schema"""
    if not ref or not ref.startswith("#/definitions/"):
        return None
    ref_name = ref.replace("#/definitions/", "")
    return definitions.get(ref_name)

def schema_to_json_example(schema, definitions, indent=0):
    """Convert schema to JSON example"""
    if not schema:
        return "{}"
    
    # Handle $ref
    if "$ref" in schema:
        ref_schema = resolve_ref(schema["$ref"], definitions)
        if ref_schema:
            return schema_to_json_example(ref_schema, definitions, indent)
        return "{}"
    
    schema_type = schema.get("type", "object")
    fmt = schema.get("format", "")
    
    if schema_type == "object":
        props = schema.get("properties", {})
        if not props:
            return "{}"
        
        items = []
        for prop_name, prop_schema in props.items():
            prop_example = schema_to_json_example(prop_schema, definitions, indent + 2)
            items.append(" " * (indent + 2) + f'"{prop_name}": {prop_example}')
        
        if not items:
            return "{}"
        
        result = "{\n" + ",\n".join(items) + "\n" + " " * indent + "}"
        return result
    
    elif schema_type == "array":
        items_schema = schema.get("items", {})
        item_example = schema_to_json_example(items_schema, definitions, indent + 2)
        return f"[\n{ ' ' * (indent + 2)}{item_example}\n{ ' ' * indent}]"
    
    elif schema_type == "string":
        if fmt == "date-time":
            return '"2025-..."'
        return '"..."'
    
    elif schema_type == "number" or schema_type == "integer":
        if fmt == "double" or fmt == "float":
            return "1000.0"
        return "1000"
    
    elif schema_type == "boolean":
        return "true"
    
    return "null"

def get_auth_requirement(operation, security_definitions):
    """Determine auth requirement for operation"""
    sec = operation.get("security")
    if sec is None:
        # Check global security
        return None
    if sec == []:
        return "không"
    return "Bearer"

# Group paths by tags
paths = data.get("paths", {})
tags_map = defaultdict(list)

if paths:
    for path, methods in sorted(paths.items()):
        for method, operation in sorted(methods.items()):
            op_tags = operation.get("tags", [])
            if not op_tags:
                op_tags = ["Other"]
            for tag in op_tags:
                tags_map[tag].append((method.upper(), path, operation))

# Generate sections by tags
if tags_map:
    for tag_name in sorted(tags_map.keys()):
        lines.append("")
        lines.append("---")
        lines.append("")
        lines.append(f"## {tag_name} Endpoints")
        
        for method, path, operation in tags_map[tag_name]:
            lines.append("")
            lines.append(f"### {method} {path}")
            
            summary = operation.get("summary", "")
            desc = operation.get("description", "")
            if summary:
                lines.append(f"- Mô tả: {summary}")
            elif desc:
                lines.append(f"- Mô tả: {desc}")
            
            # Auth
            auth = get_auth_requirement(operation, security_definitions)
            if auth:
                lines.append(f"- Auth: {auth}")
            else:
                lines.append("- Auth: không")
            
            # Request body
            params = operation.get("parameters", [])
            body_param = None
            path_params = []
            query_params = []
            
            for param in params:
                param_in = param.get("in", "")
                if param_in == "body":
                    body_param = param
                elif param_in == "path":
                    path_params.append(param)
                elif param_in == "query":
                    query_params.append(param)
            
            if body_param:
                schema = body_param.get("schema")
                if schema:
                    lines.append("- Request JSON:")
                    lines.append("")
                    json_example = schema_to_json_example(schema, definitions)
                    lines.append("```json")
                    lines.append(json_example)
                    lines.append("```")
            
            # Responses
            responses = operation.get("responses", {})
            for status_code in sorted(responses.keys(), key=lambda x: (x != "200", x)):
                resp = responses[status_code]
                desc = resp.get("description", "")
                schema = resp.get("schema")
                
                lines.append("")
                if status_code == "200":
                    lines.append(f"- Response {status_code}:")
                else:
                    lines.append(f"- Response {status_code}: {desc}")
                
                if schema:
                    json_example = schema_to_json_example(schema, definitions)
                    if json_example.strip():
                        lines.append("")
                        lines.append("```json")
                        lines.append(json_example)
                        lines.append("```")
                
                # Additional notes for non-200
                if status_code != "200" and desc:
                    lines.append("")
                    lines.append(f"Response {status_code} nếu {desc.lower()}.")

# If no paths, show schemas
if not paths and definitions:
    lines.append("")
    lines.append("---")
    lines.append("")
    lines.append("## Schemas")
    
    # Filter out common protobuf types
    skip_schemas = {"protobufAny", "rpcStatus", "googleprotobufEmpty"}
    
    for name, schema in sorted(definitions.items()):
        if name in skip_schemas:
            continue
        
        lines.append("")
        lines.append(f"### `{name}`")
        schema_desc = schema.get("description") or schema.get("title") or ""
        if schema_desc:
            lines.append(schema_desc)
        
        properties = schema.get("properties", {})
        required = set(schema.get("required", []))
        if properties:
            lines.append("")
            lines.append("| Field | Type | Required | Description |")
            lines.append("| --- | --- | --- | --- |")
            for field, meta in sorted(properties.items()):
                field_type = meta.get("type", "object")
                fmt = meta.get("format", "")
                ref = meta.get("$ref", "")
                
                type_str = field_type
                if fmt:
                    type_str += f" ({fmt})"
                if ref:
                    ref_name = ref.replace("#/definitions/", "") if ref.startswith("#/definitions/") else ""
                    if ref_name:
                        type_str += f" [{ref_name}]"
                
                desc = meta.get("description") or meta.get("title") or ""
                lines.append(f"| `{field}` | {type_str} | `{field in required}` | {desc} |")

lines.append("")
lines.append("---")
lines.append("")
lines.append("End of spec.")

output_path.write_text("\n".join(lines) + "\n")
PY
}

echo "Generating Markdown API specs into $OUT_DIR"

for swagger_file in "${SWAGGER_FILES[@]}"; do
  rel="${swagger_file#$OPENAPI_DIR/}"
  out_path="$OUT_DIR/${rel%.swagger.json}.md"
  echo " - ${rel} -> ${out_path#$ROOT_DIR/}"
  convert_file "$swagger_file" "$out_path"
done

echo "Markdown generation complete."
