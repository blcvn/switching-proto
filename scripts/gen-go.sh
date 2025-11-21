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
  if command -v "$binname" >/dev/null 2>&1; then
    echo "$binname: found"
    return 0
  fi
  echo "$binname: NOT FOUND"
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
  export PATH="$PATH:$GO_BIN_DIR"
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

mkdir -p "$OUT_DIR"
echo "Generating Go code from .proto files in $PROTO_DIR -> $OUT_DIR"

# collect proto files
PROTO_FILES=$(find "$PROTO_DIR" -name '*.proto' -print)

if [ -z "$PROTO_FILES" ]; then
  echo "No .proto files found in $PROTO_DIR" >&2
  exit 1
fi

# Build include path list (include all subdirectories under schema)
INCLUDE_PATHS=$(find "$PROTO_DIR" -mindepth 1 -type d -print | awk '{printf "-I %s ", $0}')


# Use paths=import so protoc-gen-go honors `option go_package` and writes files
# into subdirectories under $OUT_DIR that match the import path.
PROTO_MODULE_PREFIX="github.com/blcvn/switching-proto/go"

protoc $INCLUDE_PATHS \
  --go_out=module=${PROTO_MODULE_PREFIX},paths=import:"$OUT_DIR" \
  --go-grpc_out=module=${PROTO_MODULE_PREFIX},paths=import:"$OUT_DIR" \
  $PROTO_FILES

echo "Go code generation complete. Files written to $OUT_DIR"
