plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.ebdz.compose.gradleplugin.android.application"
            
            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "com.ebdz.compose.gradleplugin.application.compose"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "com.ebdz.compose.gradleplugin.library.compose"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidLibraryComposeConventionPlugin"
        }
        register("androidFrameworkLibraries") {
            id = "com.ebdz.compose.gradleplugin.android.framework"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidFrameworkLibrariesConventionPlugin"
        }
        register("androidAppDi") {
            id = "com.ebdz.compose.gradleplugin.android.di"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidAppDiConventionPlugin"
        }
        register("androidLibraryDi") {
            id = "com.ebdz.compose.gradleplugin.di"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidDiConventionPlugin"
        }
        register("androidKotlin") {
            id = "com.ebdz.compose.gradleplugin.android.kotlin"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidLibraryKotlinConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.ebdz.compose.gradleplugin.android.library"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidLibraryConventionPlugin"
        }
        register("androidTest") {
            id = "com.ebdz.compose.gradleplugin.android.test"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidTestConventionPlugin"
        }

    }
}

dependencies {
    with(baseLibs) {
        implementation(kotlinGradlePlugin)
        implementation(gradlePlugin)
        implementation(detektPlugin)
    }
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
    kotlinOptions {
        allWarningsAsErrors = false
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
