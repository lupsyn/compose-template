package com.ebdz.compose

import android.app.Application
import com.ebdz.data.local.di.androidLocalModule
import com.ebdz.data.local.di.localModule
import com.ebdz.data.remote.di.remoteModule
import com.ebdz.data.repository.di.repositoryModule
import com.ebdz.domain.di.domainModule
import com.ebdz.features.template.di.templateModule
import com.ebdz.libraries.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * [Application] class.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                coreModule,
                domainModule,
                repositoryModule,
                localModule,
                androidLocalModule(),
                remoteModule(apiBaseUrl = API_BASE_URL, enableLogging = BuildConfig.DEBUG),
                templateModule
            )
        }
    }

    private companion object {
        // Placeholder - point this at a real backend when this template becomes a real app.
        const val API_BASE_URL = "https://example.invalid"
    }
}
