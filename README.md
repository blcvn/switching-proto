# Proto workspace helper

This workspace contains proto files under `schema/` and helper scripts to generate language bindings.

- `schema/` : .proto sources (examples: `payments`, `users`, `notifications`)
- `types/` : intended output for Go generated files (by default `--go_out`)
- `java_package/` : intended output for Java generated files (by default `--java_out`)
- `scripts/gen-go.sh` : Script to generate Go code using `protoc` + `protoc-gen-go` and `protoc-gen-go-grpc`.
- `scripts/gen-java.sh` : Script to generate Java code using `protoc`.

Usage

Make the scripts executable and run them:

```bash
chmod +x scripts/gen-go.sh scripts/gen-java.sh
./scripts/gen-go.sh
./scripts/gen-java.sh
```

Requirements

- `protoc` (Protocol Buffers compiler)
- For Go generation: `protoc-gen-go` and `protoc-gen-go-grpc` installed in `GOBIN` (or in PATH). Install via:

```bash
go install google.golang.org/protobuf/cmd/protoc-gen-go@latest
go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest
```

Notes

- The `types/` and `java_package/` folders contain small placeholder files so the structure is visible. Running the scripts will overwrite or add real generated files.

Monorepo: Go + Java modules, CI/CD and versioning

- **Structure**: keep language roots separated — `go/` for Go modules and `java/` for Java/Gradle modules. Each subfolder that is a module should contain its own `go.mod` or `build.gradle`.
- **CI**: GitHub Actions workflows are added under `.github/workflows`:
	- `ci-go.yml` runs tests for Go modules when files under `go/**` change.
	- `ci-java.yml` runs Gradle builds when files under `java/**` change.
	- `release.yml` triggers on any pushed tag and runs `scripts/release.sh` to build the matching module and create a GitHub Release.
- **Tagging / Versioning**: we use Git tags to release modules. Tag naming convention:
	- `<lang>/<module>/vX.Y.Z`  (examples: `go/accesspoint/v1.2.0`, `java/payment/v1.2.0`).
	- The release workflow will parse the tag to determine which module to build and attach artifacts.
- **Go module notes**: for major versions v2+ your `module` path inside `go.mod` must include the `/vN` suffix (for example: `module github.com/yourorg/yourrepo/go/accesspoint/v2`) and tags must match that major version (e.g., `go/accesspoint/v2.0.0`).
- **Release process (example)**:

	1. Create an annotated tag for the module you want to release:

		 ```bash
		 git tag -a go/accesspoint/v1.2.0 -m "release go/accesspoint v1.2.0"
		 git push origin refs/tags/go/accesspoint/v1.2.0
		 ```

	2. Pushing the tag triggers the `release.yml` workflow which will run `scripts/release.sh` and then create a GitHub Release with any artifacts found under `release_artifacts/`.

- **Scripts**: A helper `scripts/release.sh` is provided. It expects tags in the format above and will build the targeted module and place artifacts under `release_artifacts/`.

Next steps / recommendations

- Add credentials / publishing steps if you intend to publish artifacts: e.g., GitHub Packages, Maven Central (for Java) or a storage bucket. Store secrets in GitHub Actions secrets.
- Consider automating version bumps (via Gradle properties or a small release tool) and using conventional commits or a release tool like `semantic-release` if you want fully automated versioning.

