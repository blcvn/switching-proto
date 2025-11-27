#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
PROTO_DIR="$ROOT_DIR/schema"
OUT_DIR="$ROOT_DIR/kotlin"

command -v protoc >/dev/null 2>&1 || { echo "protoc not found. Install protoc." >&2; exit 1; }
command -v protoc-gen-kotlin >/dev/null 2>&1 || { echo "protoc-gen-kotlin not found. Install kotlin protobuf plugin." >&2; exit 1; }

mkdir -p "$OUT_DIR"
echo "Generating Kotlin code from .proto files in $PROTO_DIR -> $OUT_DIR"

if ! mapfile -t PROTO_FILES < <(cd "$PROTO_DIR" && find . -name '*.proto' -print | sed 's|^\./||' | sort); then
  echo "Failed to enumerate proto files under $PROTO_DIR" >&2
  exit 1
fi

if [ "${#PROTO_FILES[@]}" -eq 0 ]; then
  echo "No .proto files found in $PROTO_DIR" >&2
  exit 1
fi

(cd "$PROTO_DIR" && \
  protoc -I . \
    --kotlin_out="$OUT_DIR" \
    "${PROTO_FILES[@]}"
)

echo "Kotlin code generation complete. Files written to $OUT_DIR"
