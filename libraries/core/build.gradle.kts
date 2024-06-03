plugins {
    id("com.ebdz.compose.gradleplugin.android.library")
    id("com.ebdz.compose.gradleplugin.di")
}

android {
    namespace = "com.ebdz.libraries.core"
}

dependencies {
    implementation(libs.lifecycleViewmodel)

    testImplementation(project(":libraries:test"))
}
