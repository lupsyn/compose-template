package com.ebdz.compose.buildsrc

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import co.vitality.about_you.buildsrc.tools.kotlinOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryKotlinConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.android")

            val extension = extensions.getByType<LibraryExtension>()

            configureKotlinAndroid(extension)
        }
    }

    private fun Project.configureKotlinAndroid(
        extension: CommonExtension<*, *, *, *, *, *>,
    ) {
        extension.apply {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            add("implementation", libs.findBundle("kotlin").get())
        }
    }

}
