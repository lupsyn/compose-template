plugins {
    id("com.ebdz.compose.gradleplugin.androidlibrary")
    id("com.ebdz.compose.gradleplugin.androidkotlin")
    id("com.ebdz.compose.gradleplugin.di")
}

android {
    namespace = "com.ebdz.libraries.extensions"
}

dependencies {
    implementation(libs.kotlinLogging)
}
