plugins {
    id("com.ebdz.compose.gradleplugin.androidlibrary")
    id("com.ebdz.compose.gradleplugin.librarycompose")
}

android {
    namespace = "com.ebdz.features.preference"
}

android {
    namespace = "com.ebdz.preference"
}

dependencies {
    implementation(project(":libraries:core"))
    implementation(project(":libraries:designsystem"))
    implementation(project(":libraries:extensions"))

    testImplementation(project(":libraries:test"))
}
