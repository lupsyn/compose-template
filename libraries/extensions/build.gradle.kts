plugins {
    id("com.ebdz.compose.gradleplugin.android.library")
    id("com.ebdz.compose.gradleplugin.android.kotlin")
    id("com.ebdz.compose.gradleplugin.di")
}

android {
    namespace = "com.ebdz.libraries.extensions"
}

dependencies {
    implementation(libs.kotlinLogging)
}
