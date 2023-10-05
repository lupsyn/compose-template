plugins {
    id("com.ebdz.compose.gradleplugin.androidlibrary")
    id("com.ebdz.compose.gradleplugin.androidkotlin")
    id("com.ebdz.compose.gradleplugin.di")
}
android {
    namespace = "com.ebdz.libraries.navigation"
}
android {
    namespace = "com.ebdz.navigation"
}



dependencies {
    api(libs.bundles.composeNavigationBundle)

    implementation(libs.ktxCore)
}
