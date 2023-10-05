plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
}

android {
    namespace = "com.ebdz.core"
}

dependencies {
    implementation(Deps.android.ktx)
    implementation(Deps.android.material)
    implementation(Deps.android.splashScreen)
    implementation(Deps.coroutines.core)
    implementation(Deps.koin.android)
}
