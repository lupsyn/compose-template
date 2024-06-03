package com.ebdz.compose

import android.app.Application
import com.ebdz.compose.di.appModule
//import com.ebdz.data.local.di.localModule
import com.ebdz.data.local.di.repositoryModule
import com.ebdz.domain.di.domainModule
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
            // Reference Android context
            androidContext(this@App)

            modules(
                appModule +
//                        coreModule +
                        domainModule +
                        repositoryModule
//                        localModule
            )
        }
    }
}
