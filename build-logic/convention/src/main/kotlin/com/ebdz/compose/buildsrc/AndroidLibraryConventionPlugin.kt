package com.ebdz.compose.buildsrc

import ConfigData
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.getByType<LibraryExtension>().configure()
        }
    }

    private fun LibraryExtension.configure() {
        setCompileSdkVersion(ConfigData.androidCompileSdkVersion)

        defaultConfig {
            multiDexEnabled = true
            minSdk = ConfigData.androidMinSdkVersion
            testInstrumentationRunner = ConfigData.testInstrumentationRunner
        }

        compileOptions {
            sourceCompatibility(JavaVersion.VERSION_17)
            targetCompatibility(JavaVersion.VERSION_17)
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
        }
    }
}
