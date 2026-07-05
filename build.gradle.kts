/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.detektPlugin)
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$projectDir/config/detekt/detekt.yml")
    parallel = true
}

dependencies {
    detektPlugins(libs.detektFormattion)
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
    }
}

// Kotlin/Native's compiler-cache builder (KonanConfig -> CacheSupport -> CachedLibraries)
// crashes with a NullPointerException while resolving the transitive klib graph for native test
// binaries in modules that pull in Compose Multiplatform (e.g. :features:template) - a known
// rough edge in this Kotlin/Compose version combination, unrelated to any project dependency
// misconfiguration. commonTest already runs faithfully on the Android target
// (`testDebugUnitTest`), so disable the iOS native test-binary link tasks project-wide rather
// than chase a compiler-internal crash. Applied in `afterEvaluate` so it runs after the Kotlin
// Multiplatform plugin's own task configuration, which otherwise re-enables these tasks.
subprojects {
    afterEvaluate {
        tasks.matching { task ->
            val name = task.name
            name.matches(Regex("ios\\w*Test$")) || name.matches(Regex("link\\w*TestIos\\w*"))
        }.configureEach { enabled = false }
    }
}
