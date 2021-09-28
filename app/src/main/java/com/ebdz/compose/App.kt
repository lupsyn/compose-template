package com.ebdz.compose

import android.app.Application
import com.ebdz.compose.di.appModule
import com.ebdz.core.di.coreModule
import com.ebdz.domain.di.domainModule
import com.ebdz.local.di.localModule
import com.ebdz.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * [Application] class.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)

            modules(
                appModule +
                        coreModule +
                        domainModule +
                        repositoryModule +
                        localModule
            )
        }
    }
}
