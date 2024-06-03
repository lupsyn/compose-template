rootProject.name = "Compose-template"

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


enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("buildSrc/libs.versions.toml"))
        }
    }
}
