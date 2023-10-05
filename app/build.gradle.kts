import extensions.addComposeConfig
import extensions.addComposeDependencies
import extensions.addSingleDeviceTestOptions

plugins {
    id(GradlePlugin.ANDROID_APPLICATION)
    id(GradlePlugin.KOTLIN_ANDROID)
    id(GradlePlugin.PARCELIZE)
}

android {
    compileSdk = Versions.compileSdk
    namespace = "com.ebdz.compose"

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        applicationId = Releases.versionAppId
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        warningsAsErrors = true
        abortOnError = false // Google needs to update their libraries to API Level 10.
        htmlReport = true
        checkDependencies = true

        lintConfig = file("${rootDir}/config/filters/lint.xml")
        htmlOutput = file("${buildDir}/reports/lint.html")
    }

    addComposeConfig()

    kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

    addSingleDeviceTestOptions()
}

dependencies {
    implementation(projects.libraries.core)
    implementation(projects.libraries.designsystem)
    implementation(projects.libraries.navigation)
    implementation(projects.data.local)
    implementation(projects.data.repository)
    implementation(projects.domain)

    implementation(Deps.logging)
    implementation(Deps.compose.navigation)
    implementation(Deps.compose.activity)
    implementation(Deps.android.playCore)
    implementation(Deps.koin.android)
    implementation(Deps.android.splashScreen)

    implementation(projects.features.preference)

    addComposeDependencies()
    debugImplementation(Deps.compose.manifest)
}

