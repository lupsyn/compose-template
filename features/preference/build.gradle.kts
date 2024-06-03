plugins {
    id("com.ebdz.compose.gradleplugin.android.library")
    id("com.ebdz.compose.gradleplugin.library.compose")
}

android {
    namespace = "com.ebdz.features.preference"
}

dependencies {
    implementation(project(":libraries:core"))
    implementation(project(":libraries:designsystem"))
    implementation(project(":libraries:extensions"))

    testImplementation(project(":libraries:test"))
}
