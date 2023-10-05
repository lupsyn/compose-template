plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.COMPOSE)
}

android {
    namespace = "com.ebdz.preference"
}

dependencies {
    implementation(projects.libraries.core)
    implementation(projects.libraries.designsystem)

    testImplementation(projects.libraries.test)

    androidTestImplementation(projects.libraries.test)
}
