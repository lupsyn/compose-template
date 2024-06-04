package com.ebdz.compose.buildsrc

import ConfigData
import co.vitality.about_you.buildsrc.tools.kotlinOptions
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            configureApplication(extensions.getByType())
        }
    }

    private fun configureApplication(
        commonExtension: ApplicationExtension,
    ) {
        commonExtension.apply {
            namespace = "com.ebdz.compose"

            compileSdk = ConfigData.androidCompileSdkVersion

            testBuildType = "debug"

            buildFeatures {
                viewBinding = true
                dataBinding = true
            }

            defaultConfig {
                minSdk = ConfigData.androidMinSdkVersion
                targetSdk = ConfigData.androidTargetSdkVersion

                applicationId = ConfigData.androidApplicationId
                versionCode = ConfigData.versionCode
                versionName = ConfigData.versionName

                testApplicationId = ConfigData.testApplicationId
                testInstrumentationRunner = ConfigData.testInstrumentationRunner
                testInstrumentationRunnerArguments += mapOf("clearPackageData" to "true")

                vectorDrawables.useSupportLibrary = true

                multiDexEnabled = true
            }

            compileOptions {
                sourceCompatibility(JavaVersion.VERSION_17)
                targetCompatibility(JavaVersion.VERSION_17)
//                isCoreLibraryDesugaringEnabled = true
            }

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }

        }
    }
}
