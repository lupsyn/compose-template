package com.ebdz.libraries.core.platform

/**
 * Platform-provided signal indicating whether the current build is a debug build.
 *
 * - Android: backed by this module's own `BuildConfig.DEBUG`.
 * - iOS: backed by Kotlin/Native `Platform.isDebugBinary`.
 */
expect val isDebugBuild: Boolean
