plugins {
    id("com.ebdz.compose.gradleplugin.android.library")
    id("com.ebdz.compose.gradleplugin.android.kotlin")
    id("com.ebdz.compose.gradleplugin.di")
    id("com.ebdz.compose.gradleplugin.android.room")
}

android {
    namespace = "com.ebdz.data.local"

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
                arguments["room.incremental"] = "true"
            }
        }
    }
    sourceSets {
        getByName("androidTest").assets.srcDirs("$projectDir/schemas")
    }
}

dependencies {
    implementation(projects.libraries.core)
    implementation(projects.data.repository)

    testImplementation(project(":libraries:test"))
}
