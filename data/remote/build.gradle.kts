plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain)

            implementation(libs.bundles.ktorCommon)
            implementation(libs.kotlinCoroutinesCore)
            implementation(libs.koin.core)
        }

        androidMain.dependencies {
            implementation(libs.bundles.ktorAndroid)
        }

        iosMain.dependencies {
            implementation(libs.bundles.ktorIos)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinCoroutinesTest)
            implementation(libs.ktor.clientMock)
        }
    }
}

android {
    namespace = "com.ebdz.data.remote"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}
