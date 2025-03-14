#!/usr/bin/env bash

set -euxo pipefail
THIS_SCRIPT_DIR="$( cd -- "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
LIB_NAME="libloro.a"
RUST_FOLDER="$THIS_SCRIPT_DIR/../loro-rs"
FRAMEWORK_NAME="loroFFI"

JAVA_FOLDER="$THIS_SCRIPT_DIR/../loro-mvn/src/main/java"
BUILD_FOLDER="$RUST_FOLDER/target"

## The specific issue with an earlier nightly version and linking into an
## XCFramework appears to be resolved with latest versions of +nightly toolchain
## (as of 10/10/23), but leaving it open to float seems less useful than
## moving the pinning forward, since Catalyst support (target macabi) still
## requires an active, nightly toolchain.
#RUST_NIGHTLY="nightly-2024-10-09"
#
#echo "Install nightly and rust-src for Catalyst"
#rustup toolchain install ${RUST_NIGHTLY}
#rustup component add rust-src --toolchain ${RUST_NIGHTLY}
#rustup update
#rustup default ${RUST_NIGHTLY}


echo "▸ Install toolchains"
rustup target add x86_64-unknown-linux-gnu # iOS Simulator (Intel)
#rustup target add x86_64-apple-ios # iOS Simulator (Intel)
#rustup target add aarch64-apple-ios-sim # iOS Simulator (M1)
#rustup target add aarch64-apple-ios # iOS Device
#rustup target add aarch64-apple-darwin # macOS ARM/M1
#rustup target add x86_64-apple-darwin # macOS Intel/x86
#rustup target add wasm32-wasi # WebAssembly
cargo_build="cargo build --manifest-path $RUST_FOLDER/Cargo.toml"
#cargo_build_nightly="cargo +${RUST_NIGHTLY} build --manifest-path $RUST_FOLDER/Cargo.toml"
#cargo_build_nightly_with_std="cargo -Zbuild-std build --manifest-path $RUST_FOLDER/Cargo.toml"

echo "▸ Clean state"
rm -rf "${JAVA_FOLDER}"
mkdir -p "${JAVA_FOLDER}"

echo "▸ Building for x86_64-unknown-linux-gnu"
CFLAGS_x86_64_unknown_linux_gnu="-target x86_64-unknown-linux-gnu" \
$cargo_build --target x86_64-unknown-linux-gnu --locked --release

echo "▸ Generate Java Scaffolding Code"
cargo run --manifest-path "$RUST_FOLDER/Cargo.toml"  \
    --bin uniffi-bindgen-java generate \
    "$RUST_FOLDER/src/loro.udl" \
    --no-format \
    --out-dir "${JAVA_FOLDER}"


cp "${BUILD_FOLDER}/x86_64-unknown-linux-gnu/release/libloro.so" "$THIS_SCRIPT_DIR/../loro-mvn/src/main/resources/linux-x86-64/libuniffi_loro.so"

#bash "${THIS_SCRIPT_DIR}/refine_trait.sh"



# copies the generated header into the build folder structure for local XCFramework usage
#mkdir -p "${BUILD_FOLDER}/includes/loroFFI"
#cp "${JAVA_FOLDER}/loroFFI.h" "${BUILD_FOLDER}/includes/loroFFI"
#cp "${JAVA_FOLDER}/loroFFI.modulemap" "${BUILD_FOLDER}/includes/loroFFI/module.modulemap"
#cp -f "${JAVA_FOLDER}/loro.java" "${THIS_SCRIPT_DIR}/../Sources/Loro/LoroFFI.java"

