#!/usr/bin/env bash
set -euo pipefail

# Run 'go mod tidy' for every submodule in the go/ folder that contains a go.mod
# Usage: ./go/scripts/go_mod_tidy_all.sh

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"

echo "Looking for go modules under $ROOT_DIR"

failures=()

for moddir in "$ROOT_DIR"/*; do
  if [ -d "$moddir" ] && [ -f "$moddir/go.mod" ]; then
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
