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
    lines.append("Authentication: hầu hết các endpoint (ngoại trừ `/health` và `/auth/token`) yêu cầu header:")
    for sec_name, sec_def in security_definitions.items():
        sec_type = sec_def.get("type", "")
        if sec_type == "apiKey":
            name = sec_def.get("name", "")
            in_loc = sec_def.get("in", "")
            if in_loc == "header":
                lines.append(f"- {name}: Bearer <token>")
            else:
                lines.append(f"- {name}: <token>")
        elif sec_type == "oauth2":
            lines.append("- Authorization: Bearer <token>")
    
    # Add token endpoint info if auth is present
    lines.append("")
    lines.append("Token endpoint trả về token mẫu:")
    lines.append("")
    lines.append("{")
    lines.append('  "access_token": "mock-access-token-12345",')
    lines.append('  "token_type": "Bearer",')
    lines.append('  "expires_in": 3600')
    lines.append("}")

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

def get_response_type_description(schema, definitions):
    """Get a human-readable description of the response type"""
    if not schema:
        return None
    
    # Handle $ref
    if "$ref" in schema:
        ref_name = schema["$ref"].replace("#/definitions/", "")
        return f"object `{ref_name}`"
    
    schema_type = schema.get("type", "object")
    
    if schema_type == "array":
        items = schema.get("items", {})
        if "$ref" in items:
            ref_name = items["$ref"].replace("#/definitions/", "")
            return f"array of `{ref_name}` objects"
        return "array of objects"
    elif schema_type == "object":
        return "object"
    elif schema_type == "string":
        return "string"
    elif schema_type in ["number", "integer"]:
        return schema_type
    elif schema_type == "boolean":
        return "boolean"
    
    return None

def enhance_description(base_desc, method, path, path_params, query_params):
    """Enhance description with path parameters and context"""
    if not base_desc:
        # Generate a basic description from method and path
        path_lower = path.lower()
        if method == "GET":
            if path_params:
                param_names = [p.get("name", "") for p in path_params]
                if len(param_names) == 1:
                    base_desc = f"Lấy thông tin theo `{param_names[0]}`"
                else:
                    base_desc = f"Lấy thông tin"
            elif "list" in path_lower or "search" in path_lower:
                base_desc = "Danh sách"
            else:
                base_desc = "Lấy thông tin"
        elif method == "POST":
            if "create" in path_lower or "new" in path_lower:
                base_desc = "Tạo mới"
            elif "confirm" in path_lower:
                base_desc = "Xác nhận"
            elif "subscribe" in path_lower:
                base_desc = "Đăng ký"
            else:
                base_desc = "Gửi yêu cầu"
        elif method == "PUT" or method == "PATCH":
            base_desc = "Cập nhật"
        elif method == "DELETE":
            if "unsubscribe" in path_lower:
                base_desc = "Hủy đăng ký"
            else:
                base_desc = "Xóa"
    
    # Add path parameter context to description
    if path_params:
        param_names = [p.get("name", "") for p in path_params]
        if len(param_names) == 1 and param_names[0] not in base_desc:
            base_desc = f"{base_desc} theo `{param_names[0]}`"
    
    return base_desc

# Group paths by tags and auth status
paths = data.get("paths", {})
tags_map = defaultdict(list)
public_endpoints = []

if paths:
    for path, methods in sorted(paths.items()):
        for method, operation in sorted(methods.items()):
            # Check if this is a public endpoint (no auth)
            auth = get_auth_requirement(operation, security_definitions)
            is_public = auth == "không" or auth is None
            
            op_tags = operation.get("tags", [])
            if not op_tags:
                op_tags = ["Other"]
            
            if is_public:
                public_endpoints.append((method.upper(), path, operation))
            else:
                for tag in op_tags:
                    tags_map[tag].append((method.upper(), path, operation))

# Generate Public endpoints section first
if public_endpoints:
    lines.append("")
    lines.append("---")
    lines.append("")
    lines.append("## Public endpoints")
    
    for method, path, operation in public_endpoints:
        lines.append("")
        lines.append(f"### {method} {path}")
        
        # Extract parameters
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
        
        # Enhanced description
        summary = operation.get("summary", "")
        desc = operation.get("description", "")
        base_desc = summary or desc
        enhanced_desc = enhance_description(base_desc, method, path, path_params, query_params)
        
        if enhanced_desc:
            lines.append(f"- Mô tả: {enhanced_desc}")
        
        # Auth
        lines.append("- Auth: không")
        
        # Request body
        if body_param:
            schema = body_param.get("schema")
            if schema:
                consumes = operation.get("consumes", [])
                is_form = "application/x-www-form-urlencoded" in consumes or "multipart/form-data" in consumes
                
                if is_form:
                    lines.append("- Request (JSON or form):")
                else:
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
            resp_desc = resp.get("description", "")
            schema = resp.get("schema")
            
            lines.append("")
            type_desc = get_response_type_description(schema, definitions)
            if status_code == "200":
                if type_desc:
                    lines.append(f"- Response {status_code} ({type_desc}):")
                else:
                    lines.append(f"- Response {status_code}:")
            else:
                if resp_desc:
                    lines.append(f"- Response {status_code}: {resp_desc}")
                else:
                    lines.append(f"- Response {status_code}:")
            
            if schema:
                json_example = schema_to_json_example(schema, definitions)
                if json_example.strip():
                    lines.append("")
                    lines.append("```json")
                    lines.append(json_example)
                    lines.append("```")

# Add Authorization section if there are protected endpoints
if tags_map:
    lines.append("")
    lines.append("---")
    lines.append("")
    lines.append("## Authorization")
    lines.append("- Tất cả các route được đăng ký vào `api` subrouter đều sử dụng `authMiddleware`.")
    lines.append("- Header bắt buộc: `Authorization: Bearer <token>`")
    if security_definitions:
        lines.append("- Trong mock, token đúng là `mock-access-token-12345`.")

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
            
            # Extract parameters
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
            
            # Enhanced description
            summary = operation.get("summary", "")
            desc = operation.get("description", "")
            base_desc = summary or desc
            enhanced_desc = enhance_description(base_desc, method, path, path_params, query_params)
            
            if enhanced_desc:
                lines.append(f"- Mô tả: {enhanced_desc}")
            
            # Auth
            auth = get_auth_requirement(operation, security_definitions)
            if auth:
                lines.append(f"- Auth: {auth}")
            else:
                lines.append("- Auth: không")
            
            # Check for SSE (Server-Sent Events)
            produces = operation.get("produces", [])
            is_sse = "text/event-stream" in produces or "event-stream" in str(operation).lower() or "sse" in path.lower()
            if is_sse:
                lines.append("- Headers: `Accept: text/event-stream` (server trả `Content-Type: text/event-stream`)")
            
            # Query parameters
            if query_params:
                lines.append("- Query parameters:")
                for qp in query_params:
                    qp_name = qp.get("name", "")
                    qp_desc = qp.get("description", "")
                    qp_required = qp.get("required", False)
                    req_text = " (required)" if qp_required else " (optional)"
                    if qp_desc:
                        lines.append(f"  - `{qp_name}`: {qp_desc}{req_text}")
                    else:
                        lines.append(f"  - `{qp_name}`{req_text}")
            
            # Request body
            if body_param:
                schema = body_param.get("schema")
                if schema:
                    # Check if it's form data
                    consumes = operation.get("consumes", [])
                    is_form = "application/x-www-form-urlencoded" in consumes or "multipart/form-data" in consumes
                    
                    if is_form:
                        lines.append("- Request (JSON or form):")
                    else:
                        lines.append("- Request JSON:")
                    lines.append("")
                    json_example = schema_to_json_example(schema, definitions)
                    lines.append("```json")
                    lines.append(json_example)
                    lines.append("```")
            
            # Responses
            responses = operation.get("responses", {})
            response_notes = []
            
            for status_code in sorted(responses.keys(), key=lambda x: (x != "200", x)):
                resp = responses[status_code]
                resp_desc = resp.get("description", "")
                schema = resp.get("schema")
                
                lines.append("")
                
                # Enhanced response description
                type_desc = get_response_type_description(schema, definitions)
                produces = operation.get("produces", [])
                is_json = "application/json" in produces or not produces
                
                if status_code == "200":
                    if type_desc:
                        if is_json:
                            lines.append(f"- Response {status_code}: {type_desc}")
                        else:
                            lines.append(f"- Response {status_code} ({type_desc}):")
                    else:
                        if is_json:
                            lines.append(f"- Response {status_code} (JSON):")
                        else:
                            lines.append(f"- Response {status_code}:")
                else:
                    if resp_desc:
                        lines.append(f"- Response {status_code}: {resp_desc}")
                    else:
                        lines.append(f"- Response {status_code}:")
                
                if schema:
                    json_example = schema_to_json_example(schema, definitions)
                    if json_example.strip():
                        lines.append("")
                        lines.append("```json")
                        lines.append(json_example)
                        lines.append("```")
                
                # Collect notes for non-200 responses
                if status_code != "200" and resp_desc:
                    # Translate common error descriptions
                    resp_desc_lower = resp_desc.lower()
                    if "not found" in resp_desc_lower or "không tìm thấy" in resp_desc_lower:
                        if path_params:
                            param_name = path_params[0].get("name", "tham số")
                            response_notes.append(f"Response {status_code} nếu không tìm thấy {param_name}.")
                        else:
                            response_notes.append(f"Response {status_code} nếu không tìm thấy.")
                    elif "unauthorized" in resp_desc_lower or "không có quyền" in resp_desc_lower:
                        response_notes.append(f"Response {status_code} nếu không có quyền truy cập.")
                    elif "bad request" in resp_desc_lower or "yêu cầu không hợp lệ" in resp_desc_lower:
                        response_notes.append(f"Response {status_code} nếu yêu cầu không hợp lệ.")
                    else:
                        response_notes.append(f"Response {status_code} nếu {resp_desc_lower}.")
            
            # Add response notes
            if response_notes:
                for note in response_notes:
                    lines.append("")
                    lines.append(note)
            
            # Add operation-level notes
            operation_notes = []
            if is_sse:
                operation_notes.append("- Events gửi:")
                operation_notes.append("  - `welcome` (khi subscribe)")
                operation_notes.append("  - `update` (các sự kiện publish từ server)")
                operation_notes.append("  - `ping` (heartbeat 30s)")
            
            # Check for special patterns in path/description
            path_lower = path.lower()
            desc_lower = enhanced_desc.lower() if enhanced_desc else ""
            
            if "mock" in desc_lower or "mock" in path_lower:
                operation_notes.append("Note: Đây là endpoint mock.")
            
            if operation_notes:
                lines.append("")
                for note in operation_notes:
                    lines.append(note)

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

# Add general notes section if there are endpoints
if paths or (public_endpoints and len(public_endpoints) > 0):
    lines.append("")
    lines.append("---")
    lines.append("")
    lines.append("## Notes chung / Behaviour")
    lines.append("- Server sử dụng in-memory stores (maps) cho dữ liệu. Dữ liệu sẽ mất khi server dừng.")
    if tags_map or public_endpoints:
        lines.append("- Một vài sample data có thể được seed sẵn để test.")

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
