plugins {
    id("com.ebdz.compose.gradleplugin.android.library")
    id("com.ebdz.compose.gradleplugin.android.kotlin")
    id("com.ebdz.compose.gradleplugin.di")
}

android {
    namespace = "com.ebdz.libraries.test"

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(projects.libraries.core)

    api(libs.bundles.test)
}
