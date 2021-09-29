plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
}

dependencies {
    implementation(Deps.android.ktx)
    implementation(Deps.android.material)
    implementation(Deps.android.splashScreen)
    implementation(Deps.coroutines.core)
    implementation(Deps.koin.android)
}
