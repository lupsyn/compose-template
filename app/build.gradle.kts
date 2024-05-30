plugins {
    id("com.ebdz.compose.gradleplugin.androidapplication")
    id("com.ebdz.compose.gradleplugin.applicationcompose")
    id("com.ebdz.compose.gradleplugin.di")
}

dependencies {
    implementation(project(":features:preference"))
    debugImplementation(libs.leakCanary)

    implementation(libs.bundles.androidFramework)

    implementation(project(":domain"))
    implementation(project(":data:local"))
    implementation(project(":data:repository"))

    implementation(project(":libraries:core"))
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:designsystem"))
    implementation(project(":libraries:extensions"))

    testImplementation(project(":libraries:test"))


    implementation("io.insert-koin:koin-android:3.5.3")
}
