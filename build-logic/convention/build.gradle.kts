import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
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
                "com.ebdz.compose.buildsrc.AndroidApplicationDiConventionPlugin"
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
        register("androidRoom") {
            id = "com.ebdz.compose.gradleplugin.android.room"
            implementationClass = "com.ebdz.compose.buildsrc.AndroidRoomConventionPlugin"
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
