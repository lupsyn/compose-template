pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Compose-template"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")

include(":libraries:core")
include(":libraries:test")
include(":libraries:designsystem")
include(":libraries:navigation")
include(":libraries:extensions")

include(":domain")
include(":data:repository")
include(":data:local")

include(":features:home")
include(":features:preference")
