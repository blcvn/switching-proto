#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
PROTO_DIR="$ROOT_DIR/schema"
OUT_DIR="$ROOT_DIR/go"

command -v protoc >/dev/null 2>&1 || { echo "protoc not found. Install protoc." >&2; exit 1; }

# Ensure protoc-gen-go is available; attempt to install if missing and `go` is present
ensure_go_plugin() {
  local binname="$1"
  local install_path="$2"
  local force_install="${3:-false}"
  if [ "$force_install" != "true" ] && command -v "$binname" >/dev/null 2>&1; then
    echo "$binname: found"
    return 0
  fi
  if [ "$force_install" = "true" ]; then
    echo "$binname: forcing reinstall from $install_path"
  else
    echo "$binname: NOT FOUND"
  fi
  if ! command -v go >/dev/null 2>&1; then
    echo "go tool not found; cannot install $binname" >&2
    return 1
  fi
  echo "Attempting to install $binname via 'go install $install_path@latest'..."
  # Attempt installation
  if ! go install "$install_path@latest"; then
    echo "go install failed for $install_path" >&2
    return 1
  fi
  # Ensure GOBIN or GOPATH/bin is on PATH for this session
  GO_BIN_DIR="$(go env GOBIN 2>/dev/null)"
  if [ -z "$GO_BIN_DIR" ]; then
    GO_PKG_BIN="$(go env GOPATH 2>/dev/null)/bin"
    GO_BIN_DIR="$GO_PKG_BIN"
  fi
  export PATH="$GO_BIN_DIR:$PATH"
  if command -v "$binname" >/dev/null 2>&1; then
    echo "$binname successfully installed and found on PATH"
    return 0
  else
    echo "$binname still not found after installation. Ensure $GO_BIN_DIR is in PATH" >&2
    return 1
  fi
}

ensure_go_plugin protoc-gen-go "google.golang.org/protobuf/cmd/protoc-gen-go" || exit 1
ensure_go_plugin protoc-gen-go-grpc "google.golang.org/grpc/cmd/protoc-gen-go-grpc" || exit 1
ensure_go_plugin protoc-gen-grpc-gateway "github.com/grpc-ecosystem/grpc-gateway/v2/protoc-gen-grpc-gateway" true || exit 1

mkdir -p "$OUT_DIR"
echo "Generating Go code from .proto files in $PROTO_DIR -> $OUT_DIR"

# collect proto files (relative paths) to avoid proto_path shadowing
if ! mapfile -t PROTO_FILES < <(cd "$PROTO_DIR" && find . -name '*.proto' -print | sed 's|^\./||' | sort); then
  echo "Failed to enumerate proto files under $PROTO_DIR" >&2
  exit 1
fi

if [ "${#PROTO_FILES[@]}" -eq 0 ]; then
  echo "No .proto files found in $PROTO_DIR" >&2
  exit 1
fi

# Use paths=import so protoc-gen-go honors `option go_package` and writes files
# into subdirectories under $OUT_DIR that match the import path.
PROTO_MODULE_PREFIX="github.com/blcvn/switching-proto/go"

# Common grpc-gateway opts. Per protoc convention, these are passed via repeated
# --grpc-gateway_opt flags instead of embedding into --grpc-gateway_out=...
GATEWAY_OUT_ARGS="module=${PROTO_MODULE_PREFIX},paths=import"
GATEWAY_PLUGIN_OPTS=(
  "module=${PROTO_MODULE_PREFIX}"
  "paths=import"
  "allow_repeated_fields_in_body=true"
)

# Generate Go and gRPC server code in a single pass (multi-package friendly)
(cd "$PROTO_DIR" && \
  protoc -I . \
    --go_out=module=${PROTO_MODULE_PREFIX},paths=import:"$OUT_DIR" \
    --go-grpc_out=module=${PROTO_MODULE_PREFIX},paths=import:"$OUT_DIR" \
    "${PROTO_FILES[@]}"
)

# Build a map of go_package aliases -> proto files to keep grpc-gateway happy.
declare -A GATEWAY_PACKAGE_MAP=()
for relpath in "${PROTO_FILES[@]}"; do
  go_pkg=$(sed -n 's/^[[:space:]]*option[[:space:]]\+go_package[[:space:]]*=[[:space:]]*"\([^"]\+\)";/\1/p' "$PROTO_DIR/$relpath" | head -n1)
  if [ -z "$go_pkg" ]; then
    echo "warning: skipping $relpath because option go_package is missing" >&2
    continue
  fi
  pkg_alias="${go_pkg##*;}"
  # Handle go_package strings without ;alias
  if [ "$pkg_alias" = "$go_pkg" ]; then
    pkg_alias="${pkg_alias##*/}"
  fi
  GATEWAY_PACKAGE_MAP["$pkg_alias"]+="$relpath "
done

# Run grpc-gateway per go package alias to avoid inconsistent package errors.
for pkg_alias in "${!GATEWAY_PACKAGE_MAP[@]}"; do
  IFS=' ' read -r -a pkg_files <<< "${GATEWAY_PACKAGE_MAP[$pkg_alias]}"
  if [ "${#pkg_files[@]}" -eq 0 ]; then
    continue
  fi
  echo "Generating grpc-gateway code for package: $pkg_alias"
  (cd "$PROTO_DIR" && \
    protoc -I . \
      --grpc-gateway_out="$GATEWAY_OUT_ARGS:$OUT_DIR" \
      $(printf ' --grpc-gateway_opt=%s' "${GATEWAY_PLUGIN_OPTS[@]}") \
      "${pkg_files[@]}"
  )
done

# Find and process YAML service definition files for grpc-gateway
if ! mapfile -t YAML_FILES < <(cd "$PROTO_DIR" && find . -name '*.yaml' -print | sed 's|^\./||' | sort 2>/dev/null); then
  YAML_FILES=()
fi

# If YAML files exist, generate gateway code with YAML configuration
# Note: Each YAML file should be processed with its corresponding proto files
if [ "${#YAML_FILES[@]}" -gt 0 ]; then
  echo "Processing YAML service definitions for grpc-gateway..."
  for yaml_file in "${YAML_FILES[@]}"; do
    yaml_dir=$(dirname "$yaml_file")
    # Find proto files in the same directory as the YAML file
    if mapfile -t proto_files_in_dir < <(cd "$PROTO_DIR" && find "$yaml_dir" -maxdepth 1 -name '*.proto' -print | sed 's|^\./||' | sort 2>/dev/null); then
      if [ "${#proto_files_in_dir[@]}" -gt 0 ]; then
        echo "Generating gateway code with YAML config: $yaml_file"
        extra_opts=("${GATEWAY_PLUGIN_OPTS[@]}" "grpc_api_configuration=${yaml_file}")
        (cd "$PROTO_DIR" && \
          protoc -I . \
            --grpc-gateway_out="$GATEWAY_OUT_ARGS:$OUT_DIR" \
            $(printf ' --grpc-gateway_opt=%s' "${extra_opts[@]}") \
            "${proto_files_in_dir[@]}"
        )
      fi
    fi
  done
fi

echo "Go code generation complete. Files written to $OUT_DIR"
