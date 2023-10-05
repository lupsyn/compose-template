package com.ebdz.compose.buildsrc

import com.android.build.gradle.TestExtension
import co.vitality.about_you.buildsrc.tools.kotlinOptions
import com.ebdz.compose.buildsrc.tools.Utils.excludeFromPackagingOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.test")
            pluginManager.apply("kotlin-android")
            pluginManager.apply("kotlin-kapt")
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

        excludeFromPackagingOptions(
            "LICENSE.txt",
            "META-INF/DEPENDENCIES",
            "META-INF/ASL2.0",
            "META-INF/NOTICE",
            "META-INF/LICENSE"
        )

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }

}
