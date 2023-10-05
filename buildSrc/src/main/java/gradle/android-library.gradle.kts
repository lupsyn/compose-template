package gradle

import Deps
import extensions.addDefaultConfig

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("gradle.quality")
}

android {
    addDefaultConfig()

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
            consumerProguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }
}

dependencies {
    implementation(Deps.logging)
}
