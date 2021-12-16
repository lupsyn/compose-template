plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
}

android {
    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
            add("**/attach_hotspot_windows.dll")
            add("META-INF/licenses/**")
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.libraries.core)

    api(Deps.test.junit)

    api(Deps.coroutines.test)
    api(Deps.coroutines.testDebug)

    api(Deps.test.mockito)
    api(Deps.test.mockitoInline)

    implementation(Deps.compose.uiTest) {
        exclude(group = "androidx.core", module = "core-ktx")
        exclude(group = "androidx.fragment", module = "fragment")
        exclude(group = "androidx.customview", module = "customview")
        exclude(group = "androidx.activity", module = "activity")
        exclude(group = "androidx.lifecycle", module = "lifecycle-runtime")
    }

    implementation(Deps.test.core)
    implementation(Deps.test.uiAutomator)
}
