plugins {
    id("com.ebdz.compose.gradleplugin.androidlibrary")
    id("com.ebdz.compose.gradleplugin.di")
}

android {
    namespace = "com.ebdz.libraries.core"
}

android {
    namespace = "com.ebdz.core"
}

dependencies {
    implementation(libs.lifecycleViewmodel)

    testImplementation(project(":libraries:test"))
}
