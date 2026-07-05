plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.ebdz.compose"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ebdz.compose"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    debugImplementation(libs.leakCanary)

    implementation(libs.bundles.androidFramework)

    implementation(projects.domain)
    implementation(projects.data.repository)
    implementation(projects.data.local)
    implementation(projects.data.remote)

    implementation(projects.libraries.core)
    implementation(projects.libraries.designsystem)
    implementation(projects.libraries.navigation)
    implementation(projects.libraries.extensions)
    implementation(projects.features.template)

    implementation(compose.runtime)
    implementation(compose.material3)
    debugImplementation(compose.uiTooling)

    implementation(libs.composeActivity)
    implementation(libs.koin.android)
    implementation(libs.koin.androidxCompose)

    testImplementation(projects.libraries.test)
}
