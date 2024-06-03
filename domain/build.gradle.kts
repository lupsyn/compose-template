plugins {
    id("com.ebdz.compose.gradleplugin.android.library")
    id("com.ebdz.compose.gradleplugin.android.kotlin")
    id("com.ebdz.compose.gradleplugin.di")
}

android {
    namespace = "com.ebdz.domain"
}

dependencies {
    testImplementation(project(":libraries:test"))
}
