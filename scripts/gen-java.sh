#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
PROTO_DIR="$ROOT_DIR/schema"
OUT_DIR="$ROOT_DIR/java"

command -v protoc >/dev/null 2>&1 || { echo "protoc not found. Install protoc." >&2; exit 1; }

mkdir -p "$OUT_DIR"
echo "Generating Java code from .proto files in $PROTO_DIR -> $OUT_DIR"

# collect proto files (relative paths) to avoid proto_path shadowing
if ! mapfile -t PROTO_FILES < <(cd "$PROTO_DIR" && find . -name '*.proto' -print | sed 's|^\./||' | sort); then
  echo "Failed to enumerate proto files under $PROTO_DIR" >&2
  exit 1
fi

if [ "${#PROTO_FILES[@]}" -eq 0 ]; then
  echo "No .proto files found in $PROTO_DIR" >&2
  exit 1
fi

# If you need gRPC java generation, ensure protoc-gen-grpc-java is installed and add --grpc-java_out
(cd "$PROTO_DIR" && \
  protoc -I . \
    --java_out="$OUT_DIR" \
    "${PROTO_FILES[@]}"
)

echo "Java code generation complete. Files written to $OUT_DIR"
