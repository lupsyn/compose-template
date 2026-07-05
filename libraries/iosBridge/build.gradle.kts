plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeTemplate"
            isStatic = true

            // The fine-grained module graph stays fine-grained internally; this framework is
            // purely a distribution seam so iosApp links one binary instead of one per module.
            export(projects.domain)
            export(projects.data.repository)
            export(projects.libraries.core)
            export(projects.libraries.designsystem)
            export(projects.libraries.navigation)
            export(projects.features.template)
        }
    }

    sourceSets {
        iosMain.dependencies {
            api(projects.domain)
            api(projects.data.repository)
            api(projects.libraries.core)
            api(projects.libraries.designsystem)
            api(projects.libraries.navigation)
            api(projects.features.template)

            implementation(projects.data.local)
            implementation(projects.data.remote)

            implementation(compose.ui)
            implementation(libs.koin.core)
        }
    }
}
