package com.ebdz.libraries.iosbridge

import androidx.compose.ui.window.ComposeUIViewController
import com.ebdz.data.local.di.iosLocalModule
import com.ebdz.data.local.di.localModule
import com.ebdz.data.remote.di.remoteModule
import com.ebdz.data.repository.di.repositoryModule
import com.ebdz.domain.di.domainModule
import com.ebdz.features.template.TemplateScreen
import com.ebdz.features.template.di.templateModule
import com.ebdz.libraries.core.di.coreModule
import com.ebdz.libraries.core.platform.isDebugBuild
import com.ebdz.libraries.designsystem.Theme
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

// Placeholder - point this at a real backend when this template becomes a real app.
private const val API_BASE_URL = "https://example.invalid"

/**
 * Starts Koin for the iOS app. Called once from Swift's `@main` App initializer - Kotlin/Native
 * exports functions whose names begin with "init" with a "do" prefix (Swift's `init` family is
 * reserved), so this is called as `MainViewControllerKt.doInitKoin()` from Swift.
 */
@Suppress("unused")
fun initKoin() {
    startKoin {
        modules(
            coreModule,
            domainModule,
            repositoryModule,
            localModule,
            iosLocalModule(),
            remoteModule(apiBaseUrl = API_BASE_URL, enableLogging = isDebugBuild),
            templateModule
        )
    }
}

/** Swift's entry point into the shared Compose UI. */
@Suppress("unused", "FunctionName")
fun MainViewController(): UIViewController = ComposeUIViewController {
    Theme {
        TemplateScreen(onShowMessage = { /* wire to a native alert when this becomes a real app */ })
    }
}
