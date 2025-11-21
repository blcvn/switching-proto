Java bindings and Gradle multi-module setup (moved to `java/`)

This folder contains Java files generated from the project's `.proto` files under `java/com/blcvn/switching/...` and a Gradle multi-module project that exposes each top-level package as a separate module:

- `:accesspoint` — sources taken from `java/com/blcvn/switching/accesspoint`
- `:payment` — sources taken from `java/com/blcvn/switching/payment`
- `:user` — sources taken from `java/com/blcvn/switching/user`
- `:notification` — sources taken from `java/com/blcvn/switching/notification`

Build locally

From the repository root:

```bash
cd java
./scripts/build-modules.sh
```

If you prefer to call Gradle directly and you've checked in the Gradle wrapper, you can run:

```bash
./gradlew :accesspoint:build
```

Using JitPack: push the repository, tag a release, and reference module artifacts as previously documented.
