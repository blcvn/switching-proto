#!/usr/bin/env bash
set -euo pipefail

# Ensure every subfolder that contains Go files has a module and run go mod tidy
# Usage: ./go/scripts/go_mod_tidy_all.sh

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
MODULE_PREFIX="github.com/blcvn/switching-proto/go"
DEFAULT_GO_VERSION="${GO_DEFAULT_VERSION:-1.24.0}"

echo "Looking for go modules under $ROOT_DIR"

failures=()

for moddir in "$ROOT_DIR"/*; do
  [ -d "$moddir" ] || continue

  first_go_file="$(find "$moddir" -name '*.go' -print -quit)"
  has_go_files=false
  if [ -n "$first_go_file" ]; then
    has_go_files=true
  fi

  if [ ! -f "$moddir/go.mod" ]; then
    if [ "$has_go_files" = false ]; then
      continue
    fi
    module_name="$MODULE_PREFIX/$(basename "$moddir")"
    echo "\n==> Initializing Go module $module_name in $moddir"
    pushd "$moddir" >/dev/null
    go mod init "$module_name"
    go mod edit -go="$DEFAULT_GO_VERSION"
    popd >/dev/null
  fi

  if [ -f "$moddir/go.mod" ]; then
    if [ "$has_go_files" = false ]; then
      echo "\n==> Skipping '$moddir' (has go.mod but no Go files)"
      continue
    fi
    echo "\n==> Running 'go mod tidy' in: $moddir"
    pushd "$moddir" >/dev/null
    if go mod tidy; then
      echo "ok: go mod tidy succeeded in $moddir"
    else
      echo "FAILED: go mod tidy failed in $moddir" >&2
      failures+=("$moddir")
    fi
    popd >/dev/null
  fi
done

if [ ${#failures[@]} -ne 0 ]; then
  echo "\nSome modules failed to tidy:" >&2
  for f in "${failures[@]}"; do
    echo " - $f" >&2
  done
  exit 1
fi

echo "\nAll go modules tidy completed successfully."
