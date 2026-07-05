package com.ebdz.libraries.core.platform

@OptIn(kotlin.experimental.ExperimentalNativeApi::class)
actual val isDebugBuild: Boolean = kotlin.native.Platform.isDebugBinary
