plugins {
    id("com.ebdz.compose.gradleplugin.android.library")
    id("com.ebdz.compose.gradleplugin.android.kotlin")
    id("com.ebdz.compose.gradleplugin.di")
    // For icons in this case but not needed redundant here.
    id("com.ebdz.compose.gradleplugin.library.compose")
}

android {
    namespace = "com.ebdz.libraries.navigation"
}

dependencies {
    api(libs.bundles.composeNavigationBundle)

    implementation(libs.ktxCore)
}
