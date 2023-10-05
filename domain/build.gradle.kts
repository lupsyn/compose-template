plugins {
    id("com.ebdz.compose.gradleplugin.androidlibrary")
    id("com.ebdz.compose.gradleplugin.androidkotlin")
    id("com.ebdz.compose.gradleplugin.di")
}

android {
    namespace = "com.ebdz.domain"
//    defaultConfig {
//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments["room.schemaLocation"] = "$projectDir/schemas"
//                arguments["room.incremental"] = "true"
//            }
//        }
//    }
//    sourceSets {
//        getByName("androidTest").assets.srcDirs("$projectDir/schemas")
//    }
}

dependencies {
    testImplementation(project(":libraries:test"))
}
