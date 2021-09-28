# compose-android-template 🤖

A simple Github template that lets you create an **Android/Kotlin** project and be up and running in a **few seconds**. 

This template is focused on delivering a project with solid architecture and **continuous integration** already in place.

## How to use 👣

Just click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/lupsyn/compose-android-template/generate) button to create a new repo starting from this template.

Once created don't forget to update the:
- App id
- AndroidManifest
- Package of the source files

## Features 🎨

- **100% Kotlin-only template**.
- Dependecy injection with Koin already in place  
- Data, domain, libraries and feature module
- Sample Compose UI test
- 100% Gradle Kotlin DSL setup.
- CI Setup with GitHub Actions
- Dependency versions managed via `buildSrc`.
- Kotlin Static Analysis via `ktlint`
- Issues Template (bug report + feature request).
- Pull Request Template.

## Gradle Setup 🐘

This template is using [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as well as the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) to setup the build.

## Static Analysis 🔍

This template is using [**ktlint**](https://github.com/pinterest/ktlint) with the [ktlint-gradle](https://github.com/jlleitschuh/ktlint-gradle) plugin to format your code. To reformat all the source code as well as the buildscript you can run the `ktlintFormat` gradle task.

## CI ⚙️

This template is using [**GitHub Actions**](https://github.com/lupsyn/compose-android-template/actions) as CI. You don't need to setup any external service and you should have a running CI once you start using this template.

There are currently the following workflows available:
- [Validate Gradle Wrapper](.github/workflows/gradle-wrapper-validation.yml) - Will check that the gradle wrapper has a valid checksum
- [Pre Merge Checks](.github/workflows/pre-merge.yaml) - Will run the `build`, `check` and `publishToMavenLocal` tasks.

## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.

This repo takes inspiration from https://github.com/cortinico/kotlin-android-template 




