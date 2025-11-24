# Switching Proto Workspace

Proto definitions, generated code, and release scripts for both Go and Java services that power the VNPAY Switching platform.

## Layout

- `schema/` – canonical `.proto` sources grouped by domain (`access-point`, `offchain`, `payments`, `notifications`, `users`, ...).
- `go/` – Go modules generated from the schemas (`accesspoint`, `offchain`, `payment`, `notification`, `user`). Each module has its own `go.mod`.
- `java/` – Gradle subprojects mirroring the same domains with generated Java sources under `com/blcvn/switching/**`.
- `scripts/` – shared helpers such as `gen-go.sh`, `gen-java.sh`, and `release.sh`.
- `release_artifacts/` – populated by the release workflow (artifacts per module + version).

## Prerequisites

- `protoc` (>=3.21).
- `protoc-gen-go` and `protoc-gen-go-grpc` on your `PATH` for Go generation:

```bash
go install google.golang.org/protobuf/cmd/protoc-gen-go@latest
go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest
```

- Java toolchain (Gradle 8+, JDK 17) for building the Java modules.

## Generate sources

Run the helper scripts from the repo root (they handle all domains listed in `schema/`):

```bash
chmod +x scripts/gen-go.sh scripts/gen-java.sh
./scripts/gen-go.sh        # writes into go/**
./scripts/gen-java.sh      # writes into java/**
```

For bulk Go maintenance you can also run `go/scripts/go_mod_tidy_all.sh`, which tidies every Go module to keep `go.sum` files in sync.

Java modules can be built individually via Gradle (`./gradlew :accesspoint:build`, etc.) or all at once using `java/scripts/build-modules.sh`.

## CI/CD & Release process

- `.github/workflows/ci-go.yml` runs when files under `go/**` change.
- `.github/workflows/ci-java.yml` runs for changes under `java/**`.
- `.github/workflows/release.yml` is tag-driven and executes `scripts/release.sh`, which:
  1. Detects the module from the tag pattern `<lang>/<module>/vX.Y.Z`.
  2. Builds the corresponding Go or Java artifact.
  3. Places outputs under `release_artifacts/<module>_<version>/`.
  4. Uploads the artifact set to the GitHub Release.

Example release:

```bash
git tag -a go/accesspoint/v1.2.0 -m "release go/accesspoint v1.2.0"
git push origin refs/tags/go/accesspoint/v1.2.0
```

For Go modules with major versions ≥2, remember to add the `/vN` suffix to the module path in `go.mod` and match it in the tag (e.g., `go/accesspoint/v2.0.0`).

## Recommendations

- Store publishing credentials (Maven, artifact buckets, etc.) as GitHub Actions secrets to automate distribution.
- Adopt conventional commits or another release automation tool if you want fully automated version bumps.
- Keep generated code committed only when necessary; otherwise, rely on scripts during CI to avoid large diffs. 

