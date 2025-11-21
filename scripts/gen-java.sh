#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
PROTO_DIR="$ROOT_DIR/schema"
OUT_DIR="$ROOT_DIR/java"

command -v protoc >/dev/null 2>&1 || { echo "protoc not found. Install protoc." >&2; exit 1; }

mkdir -p "$OUT_DIR"
echo "Generating Java code from .proto files in $PROTO_DIR -> $OUT_DIR"

PROTO_FILES=$(find "$PROTO_DIR" -name '*.proto' -print)

if [ -z "$PROTO_FILES" ]; then
  echo "No .proto files found in $PROTO_DIR" >&2
  exit 1
fi

# Build include path list (include all subdirectories under schema)
INCLUDE_PATHS=$(find "$PROTO_DIR" -mindepth 1 -type d -print | awk '{printf "-I %s ", $0}')

# If you need gRPC java generation, ensure protoc-gen-grpc-java is installed and add --grpc-java_out
protoc $INCLUDE_PATHS \
  --java_out="$OUT_DIR" \
  $PROTO_FILES

echo "Java code generation complete. Files written to $OUT_DIR"
