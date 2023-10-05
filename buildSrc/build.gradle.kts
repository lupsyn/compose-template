repositories {
    google()
    mavenCentral()
}

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

object PluginsVersions {
    const val gradle = "8.1.1"
    const val kotlin = "1.9.10"
    const val detekt = "1.23.1"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.gradle}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.kotlin}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.detekt}")
}
