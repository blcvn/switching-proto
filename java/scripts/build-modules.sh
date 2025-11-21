#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT_DIR"

if [ -x "./gradlew" ]; then
  GRADLE_CMD="./gradlew"
elif command -v gradle >/dev/null 2>&1; then
  GRADLE_CMD="gradle"
else
  echo "Neither ./gradlew nor gradle was found. Install Gradle or add a Gradle wrapper to java/." >&2
  exit 1
fi

modules=()
for d in */ ; do
  if [ -f "$d/build.gradle" ] || [ -f "$d/build.gradle.kts" ]; then
    modules+=("${d%/}")
  fi
done

if [ ${#modules[@]} -eq 0 ]; then
  echo "No Gradle modules found in $ROOT_DIR" >&2
  exit 1
fi

echo "Found modules: ${modules[*]}"

tasks=()
for m in "${modules[@]}"; do
  tasks+=(":${m}:build")
done

echo "Running: $GRADLE_CMD ${tasks[*]}"
$GRADLE_CMD "${tasks[@]}"

echo "Build finished for modules: ${modules[*]}"
