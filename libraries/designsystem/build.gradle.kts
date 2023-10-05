plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.COMPOSE)
}

android {
    namespace = "com.ebdz.designsystem"
}

dependencies {
    implementation(Deps.koin.android)
    implementation(Deps.compose.viewModel)
}
