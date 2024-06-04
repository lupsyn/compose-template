package com.ebdz.compose.buildsrc

import ConfigData
import co.vitality.about_you.buildsrc.tools.kotlinOptions
import com.android.build.gradle.TestExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.test")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.getByType<TestExtension>().configure()
        }
    }

    private fun TestExtension.configure() {
        setCompileSdkVersion(ConfigData.androidCompileSdkVersion)

        experimentalProperties["android.experimental.self-instrumenting"] = true
        targetProjectPath = ":app"

        defaultConfig {
            multiDexEnabled = true
            minSdk = ConfigData.androidMinSdkVersion
            targetSdk = ConfigData.androidTargetSdkVersion
            testInstrumentationRunner = ConfigData.testInstrumentationRunner
        }

        compileOptions {
            sourceCompatibility(JavaVersion.VERSION_17)
            targetCompatibility(JavaVersion.VERSION_17)
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }

}
