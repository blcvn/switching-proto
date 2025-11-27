#!/usr/bin/env bash
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"

usage() {
  echo "Usage: $0 <lang/module> <version>  OR  $0 <lang/module/version>"
  echo "  Examples: $0 go/accesspoint v1.2.0   OR   $0 go/accesspoint/v1.2.0"
  exit 1
}

if [ $# -eq 0 ]; then
  usage
fi

if [ $# -eq 1 ]; then
  TAG="$1"
  IFS='/' read -r LANG MODULE VERSION <<< "$TAG"
elif [ $# -eq 2 ]; then
  LANG_MODULE="$1"
  VERSION="$2"
  IFS='/' read -r LANG MODULE <<< "$LANG_MODULE"
  TAG="${LANG}/${MODULE}/${VERSION}"
else
  usage
fi

if [ -z "${LANG:-}" ] || [ -z "${MODULE:-}" ] || [ -z "${VERSION:-}" ]; then
  usage
fi

ARTIFACTS_DIR="$REPO_ROOT/release_artifacts/${LANG}_${MODULE}_${VERSION}"
mkdir -p "$ARTIFACTS_DIR"

release_one() {
  local lang="$1"
  local module="$2"
  local version="$3"
  local tag="${lang}/${module}/${version}"
  local moddir="$REPO_ROOT/${lang}/${module}"

  if [ ! -d "$moddir" ]; then
    echo "Module directory not found: $moddir"
    return 1
  fi

  echo "Building $lang module at $moddir"
  case "$lang" in
    go)
      (cd "$moddir" && go build -o "$ARTIFACTS_DIR/${module}-${version#v}" ./...)
      echo "Artifact: $ARTIFACTS_DIR/${module}-${version#v}"
      ;;
    java)
      (cd "$moddir" && if [ -f ./gradlew ]; then ./gradlew assemble; else gradle assemble; fi)
      JAR=$(find "$moddir/build/libs" -type f -name "*.jar" | head -n1 || true)
      if [ -n "$JAR" ]; then
        cp "$JAR" "$ARTIFACTS_DIR/"
        echo "Artifact copied: $ARTIFACTS_DIR/$(basename "$JAR")"
      else
        echo "No jar found for $module"
      fi
      ;;
    *)
      echo "Unsupported language: $lang"
      return 1
      ;;
  esac

  # If there are uncommitted changes in the module, commit them with a preparatory message
  if [ -n "$(git status --porcelain -- "$moddir")" ]; then
    echo "Uncommitted changes detected in $moddir. Adding and committing them."
    git add "$moddir"
    git commit -m "chore(release): prepare ${tag}" || true
  fi

  # Create annotated tag and push
  echo "Creating tag: $tag"
  git tag -a "$tag" -m "Release $tag"
  echo "Pushing tag: $tag"
  git push origin "$tag"

  echo "Released $tag"
}

if [ "$MODULE" = "all" ] || [ "$MODULE" = "*" ]; then
  # release all modules for the given language(s)
  echo "Releasing all modules for language: $LANG with version $VERSION"
  for langdir in go java; do
    if [ -d "$REPO_ROOT/$langdir" ]; then
      for m in $(ls -1 "$REPO_ROOT/$langdir" | tr '\n' ' '); do
        # skip files, consider only directories
        if [ -d "$REPO_ROOT/$langdir/$m" ]; then
          release_one "$langdir" "$m" "$VERSION"
        fi
      done
    fi
  done
else
  echo "Releasing single module: $TAG"
  release_one "$LANG" "$MODULE" "$VERSION"
fi

echo "Release script completed. Artifacts in: $ARTIFACTS_DIR"
