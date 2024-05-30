package com.ebdz.compose.buildsrc

import com.android.build.gradle.LibraryExtension
import com.ebdz.compose.buildsrc.tools.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            configureCompose(extensions.getByType<LibraryExtension>())
        }
    }

}
