#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
PROTO_DIR="$ROOT_DIR/schema"
OUT_DIR="$ROOT_DIR/openapi"

OPENAPI_PLUGIN_BIN="protoc-gen-openapiv2"
OPENAPI_PLUGIN_PKG="github.com/grpc-ecosystem/grpc-gateway/v2/protoc-gen-openapiv2"

command -v protoc >/dev/null 2>&1 || { echo "protoc not found. Install protoc." >&2; exit 1; }

ensure_go_plugin() {
  local binname="$1"
  local pkg="$2"
  if command -v "$binname" >/dev/null 2>&1; then
    echo "$binname: found"
    return 0
  fi

  if ! command -v go >/dev/null 2>&1; then
    echo "$binname: NOT FOUND and Go toolchain missing. Install Go to continue." >&2
    return 1
  fi

  echo "$binname: NOT FOUND. Attempting go install $pkg@latest ..."
  if ! go install "$pkg@latest"; then
    echo "Failed to install $pkg" >&2
    return 1
  fi

  local go_bin_dir
  go_bin_dir="$(go env GOBIN 2>/dev/null)"
  if [ -z "$go_bin_dir" ]; then
    go_bin_dir="$(go env GOPATH 2>/dev/null)/bin"
  fi

  if [ -n "$go_bin_dir" ]; then
    export PATH="$PATH:$go_bin_dir"
  fi

  if command -v "$binname" >/dev/null 2>&1; then
    echo "$binname: installed"
    return 0
  fi

  echo "$binname: still not found after installation. Ensure Go bin directory is on PATH." >&2
  return 1
}

ensure_go_plugin "$OPENAPI_PLUGIN_BIN" "$OPENAPI_PLUGIN_PKG" || exit 1

mkdir -p "$OUT_DIR"
echo "Generating OpenAPI specs from .proto files in $PROTO_DIR -> $OUT_DIR"

if ! mapfile -t PROTO_FILES < <(cd "$PROTO_DIR" && find . -name '*.proto' -print | sed 's|^\./||' | sort); then
  echo "Failed to enumerate proto files under $PROTO_DIR" >&2
  exit 1
fi

if [ "${#PROTO_FILES[@]}" -eq 0 ]; then
  echo "No .proto files found in $PROTO_DIR" >&2
  exit 1
fi

OPENAPI_OPTS="${OPENAPI_OPTS:-logtostderr=true,json_names_for_fields=false}"

# Generate OpenAPI specs from proto files
(cd "$PROTO_DIR" && \
  protoc -I . \
    --openapiv2_out="$OUT_DIR" \
    --openapiv2_opt="$OPENAPI_OPTS" \
    "${PROTO_FILES[@]}"
)

# Find and process YAML service definition files for enhanced OpenAPI generation
if ! mapfile -t YAML_FILES < <(cd "$PROTO_DIR" && find . -name '*.yaml' -print | sed 's|^\./||' | sort 2>/dev/null); then
  YAML_FILES=()
fi

# If YAML files exist, regenerate OpenAPI with YAML configuration for better HTTP mapping
if [ "${#YAML_FILES[@]}" -gt 0 ]; then
  echo "Processing YAML service definitions for OpenAPI generation..."
  for yaml_file in "${YAML_FILES[@]}"; do
    yaml_dir=$(dirname "$yaml_file")
    yaml_base=$(basename "$yaml_file")
    proto_targets=()

    # Prefer a proto that matches the YAML naming convention (e.g. foo_service.yaml -> foo.proto)
    if [[ "$yaml_base" == *_service.yaml ]]; then
      proto_guess="${yaml_file%_service.yaml}.proto"
      if [ -f "$PROTO_DIR/$proto_guess" ]; then
        proto_targets=("$proto_guess")
      fi
    fi

    # Fallback: grab all protos in the same directory (older behaviour)
    if [ "${#proto_targets[@]}" -eq 0 ]; then
      if mapfile -t proto_targets < <(cd "$PROTO_DIR" && find "$yaml_dir" -maxdepth 1 -name '*.proto' -print | sed 's|^\./||' | sort 2>/dev/null); then
        :
      fi
    fi

    if [ "${#proto_targets[@]}" -eq 0 ]; then
      echo "Warning: Không tìm thấy file .proto phù hợp cho $yaml_file, bỏ qua." >&2
      continue
    fi

    echo "Generating OpenAPI with YAML config: $yaml_file (${proto_targets[*]})"
    (cd "$PROTO_DIR" && \
      protoc -I . \
        --openapiv2_out="$OUT_DIR" \
        --openapiv2_opt="${OPENAPI_OPTS},grpc_api_configuration=${yaml_file}" \
        "${proto_targets[@]}"
    )
  done
fi

echo "OpenAPI generation complete. Files written to $OUT_DIR"
