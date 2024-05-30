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
            id = "com.ebdz.compose.gradleplugin.androidapplication"
            
            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "com.ebdz.compose.gradleplugin.applicationcompose"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "com.ebdz.compose.gradleplugin.librarycompose"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidLibraryComposeConventionPlugin"
        }
        register("androidFrameworkLibraries") {
            id = "com.ebdz.compose.gradleplugin.androidframework"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidFrameworkLibrariesConventionPlugin"
        }
        register("androidLibraryDI") {
            id = "com.ebdz.compose.gradleplugin.di"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidDIConventionPlugin"
        }
        register("androidKotlin") {
            id = "com.ebdz.compose.gradleplugin.androidkotlin"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidLibraryKotlinConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.ebdz.compose.gradleplugin.androidlibrary"

            implementationClass =
                "com.ebdz.compose.buildsrc.AndroidLibraryConventionPlugin"
        }
        register("androidTest") {
            id = "com.ebdz.compose.gradleplugin.androidtest"

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
