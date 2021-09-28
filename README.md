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
- [Publish Snapshot](.github/workflows/publish-snapshot.yaml) - Will publish a `-SNAPSHOT` of the libraries to Sonatype.
- [Publish Release](.github/workflows/publish-release.yaml) - Will publish a new release version of the libraries to Maven Central on tag pushes.

## Publishing 🚀

The template is setup to be **ready to publish** a artifact on a Maven Repository.

For every module you want to publish you simply have to add the `publish` plugin:

```
plugins {
    publish
}
```

### To Maven Central

In order to use this template to publish on Maven Central, you need to configure some secrets on your repository:

| Secret name | Value |
| --- | --- | 
| `ORG_GRADLE_PROJECT_NEXUS_USERNAME` | The username you use to access Sonatype's services (such as [Nexus](https://oss.sonatype.org/) and [Jira](https://issues.sonatype.org/)) |
| `ORG_GRADLE_PROJECT_NEXUS_PASSWORD` | The password you use to access Sonatype's services (such as [Nexus](https://oss.sonatype.org/) and [Jira](https://issues.sonatype.org/)) |
| `ORG_GRADLE_PROJECT_SIGNING_KEY` | The GPG Private key to sign your artifacts. You can obtain it with `gpg --armor --export-secret-keys <your@email.here>` or you can create one key online on [pgpkeygen.com](https://pgpkeygen.com). The key starts with a `-----BEGIN PGP PRIVATE KEY BLOCK-----`. |
| `ORG_GRADLE_PROJECT_SIGNING_PWD` | The passphrase to unlock your private key (you picked it when creating the key). |

The template already sets up [Dokka](https://kotlin.github.io/dokka/) for project documentation and attaches `-sources.jar` to your publications.

Once set up, the following workflows will take care of publishing:

- [Publish Snapshot](.github/workflows/publish-snapshot.yaml) - To publish `-SNAPSHOT` versions to Sonatype. The workflow is setup to run either manually (with `workflow_dispatch`) or on every merge.
- [Publish Release](.github/workflows/publish-release.yaml) - Will publish a new release version of the libraries to Maven Central on tag pushes. You can trigger the workflow also manually if needed.

### To Jitpack

If you're using [JitPack](https://jitpack.io/), you don't need any further configuration and you can just configure the repo on JitPack.

You probably want to disable the [Publish Snapshot] and [Publish Release](.github/workflows/publish-release.yaml) workflows (delete the files), as Jitpack will take care of that for you.

## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.

This repo takes inspiration from https://github.com/cortinico/kotlin-android-template 




