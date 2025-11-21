#!/usr/bin/env bash
set -euo pipefail

TAG="$1"
echo "Release script running for tag: $TAG"

# Expected tag format: <lang>/<module>/vX.Y.Z  (examples: go/accesspoint/v1.2.0, java/payment/v1.2.0)
IFS='/' read -r LANG MODULE VERSION <<< "$TAG"

if [ -z "${LANG}" ] || [ -z "${MODULE}" ] || [ -z "${VERSION}" ]; then
  echo "Tag must be in format <lang>/<module>/vX.Y.Z"
  exit 1
fi

ARTIFACTS_DIR="release_artifacts/${LANG}_${MODULE}_${VERSION}"
mkdir -p "$ARTIFACTS_DIR"

case "$LANG" in
  go)
    MODDIR="go/${MODULE}"
    if [ ! -d "$MODDIR" ]; then
      echo "Go module directory not found: $MODDIR"
      exit 1
    fi
    echo "Building Go module at $MODDIR"
    (cd "$MODDIR" && go build -o "$PWD/../../$ARTIFACTS_DIR/${MODULE}-${VERSION#v}" ./...)
    echo "Artifact: $ARTIFACTS_DIR/${MODULE}-${VERSION#v}"
    ;;
  java)
    MODDIR="java/${MODULE}"
    if [ ! -d "$MODDIR" ]; then
      echo "Java module directory not found: $MODDIR"
      exit 1
    fi
    echo "Building Java module at $MODDIR"
    cd "$MODDIR"
    if [ -f ./gradlew ]; then
      ./gradlew assemble
    else
      gradle assemble
    fi
    # find jar(s)
    JAR=$(find build/libs -type f -name "*.jar" | head -n1 || true)
    if [ -n "$JAR" ]; then
      cp "$JAR" "$PWD/../../$ARTIFACTS_DIR/"
      echo "Artifact copied: $ARTIFACTS_DIR/$(basename "$JAR")"
    else
      echo "No jar found in build/libs"
    fi
    ;;
  *)
    echo "Unsupported language prefix: $LANG"
    exit 1
    ;;
esac

echo "Release script completed. Artifacts in: $ARTIFACTS_DIR"
