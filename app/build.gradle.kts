plugins {
    id("com.ebdz.compose.gradleplugin.android.application")
    id("com.ebdz.compose.gradleplugin.application.compose")
    id("com.ebdz.compose.gradleplugin.di")
    id("com.ebdz.compose.gradleplugin.android.di")
}

dependencies {
    debugImplementation(libs.leakCanary)

    implementation(libs.bundles.androidFramework)

    implementation(project(":domain"))

    implementation(project(":data:repository"))

    implementation(project(":libraries:core"))
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:designsystem"))
    implementation(project(":libraries:extensions"))

    testImplementation(project(":libraries:test"))
}
