package gradle

import extensions.addComposeConfig
import extensions.addComposeDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    addComposeConfig()
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }
}

dependencies {
    addComposeDependencies()
}
