package com.ebdz.compose.buildsrc

import com.android.build.api.dsl.ApplicationExtension
import com.ebdz.compose.buildsrc.tools.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            configureCompose(extensions.getByType<ApplicationExtension>())
        }
    }

}
