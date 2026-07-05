# compose-multiplatform-template 🤖

A GitHub template for bootstrapping a **Kotlin Multiplatform** app (Android + iOS) with **Compose Multiplatform**, ready to fork and build a real feature on top of.

This template favors a fine-grained, multi-module architecture with an MVI-Redux presentation layer, Koin for dependency injection, Ktor for networking, and Room for local persistence — all shared across platforms.

## How to use 👣

Click [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/lupsyn/compose-template/generate) to create a new repo starting from this template.

Once created, don't forget to update:
- The Android `applicationId` (`app/build.gradle.kts`) and iOS bundle id/app name (`iosApp/Configuration/Config.xcconfig`)
- The `AndroidManifest.xml`
- The `com.ebdz.*` package names across every module

## Architecture 🏗️

```
:app                    Android application entry point (plain Android, not KMP)
:iosApp                 iOS application entry point (Xcode project, not a Gradle module)

:libraries:core         MVI base (UiState/UiEvent/UiAction/UiEffect/Reducer/BaseViewModel)
:libraries:designsystem Compose Multiplatform + Material 3 theme and components
:libraries:navigation   Shared navigation destinations (KMP navigation-compose)
:libraries:extensions   Platform-specific extension functions
:libraries:test         Shared test utilities (e.g. MainDispatcherRule)
:libraries:iosBridge    Sole producer of the iOS .framework, exporting the module graph
                        so Xcode links one binary instead of one per module

:domain                 Use cases, domain models, repository interfaces (pure Kotlin)
:data:local             Room (KMP) local persistence
:data:remote            Ktor HTTP client with typed errors and retry/backoff
:data:repository        Repository implementations composing :data:local / :data:remote

:features:template      Skeleton feature demonstrating the full MVI stack end to end -
                        copy this shape when starting a real feature
```

Every module under `libraries`, `domain`, `data`, and `features` targets Android and iOS (`androidTarget`, `iosX64`, `iosArm64`, `iosSimulatorArm64`). `:app` stays a plain Android module; `:libraries:iosBridge` is the only module that produces an iOS framework, keeping the fine-grained module boundaries intact while giving Xcode a single binary to link.

## Features 🎨

- **100% Kotlin Multiplatform** - one codebase for Android and iOS via Compose Multiplatform
- **MVI-Redux presentation layer** with a dedicated one-off effect channel (`UiEffect`/`effectsFlow`) for navigation/toast-style events, separate from persisted UI state
- Dependency injection with **Koin**, split into common + per-platform modules
- Networking with **Ktor** (typed errors, retry with exponential backoff)
- Local persistence with **Room** (Kotlin Multiplatform)
- Fine-grained data/domain/libraries/feature module structure
- 100% Gradle Kotlin DSL setup, plugins applied directly per module (no custom convention-plugin layer)
- CI setup with GitHub Actions
- Kotlin static analysis via **Detekt**
- Issue templates (bug report + feature request) and a pull request template

## Gradle Setup 🐘

This template uses [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) and the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block). Every module applies its own plugins via [version catalog](gradle/libs.versions.toml) aliases - there's no shared convention-plugin build, so `compileSdk`/`minSdk`/JVM target are declared inline per module.

## iOS 🍎

`iosApp/` is a standard Xcode project. Its build links against the single framework produced by `:libraries:iosBridge` via a Gradle run-script build phase (`embedAndSignAppleFrameworkForXcode`) - no CocoaPods required. Open `iosApp/iosApp.xcodeproj` in Xcode and run.

> iOS builds (and Kotlin/Native in general) require a macOS host with Xcode installed.

## Static Analysis 🔍

This template uses [**Detekt**](https://github.com/detekt/detekt), configured at the root with [`config/detekt/detekt.yml`](config/detekt/detekt.yml). Run it with `./gradlew detekt`.

## CI ⚙️

This template uses [**GitHub Actions**](../../actions) as CI. You don't need to set up any external service to get a running CI once you start using this template.

Current workflows:
- [Validate Gradle Wrapper](.github/workflows/gradle-wrapper-validation.yml) - checks that the Gradle wrapper has a valid checksum
- [Pre Merge Checks](.github/workflows/pre-merge.yaml) - runs the `build` and `check` tasks on JDK 21 across Ubuntu, macOS, and Windows runners

> iOS targets only compile on the macOS runner - the Kotlin Multiplatform plugin automatically skips them on Ubuntu/Windows, so `build`/`check` still pass there.

## Contributing 🤝

Feel free to open an issue or submit a pull request for any bugs/improvements.

This repo takes inspiration from https://github.com/cortinico/kotlin-android-template
