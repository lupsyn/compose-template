plugins {
    id("com.ebdz.compose.gradleplugin.androidlibrary")
    id("com.ebdz.compose.gradleplugin.androidkotlin")
    id("com.ebdz.compose.gradleplugin.di")
    id("com.ebdz.compose.gradleplugin.librarycompose")
}

android {
    namespace = "com.ebdz.libraries.designsystem"
}

android {
    namespace = "com.ebdz.designsystem"
}

dependencies {
    testImplementation(project(":libraries:test"))
}
