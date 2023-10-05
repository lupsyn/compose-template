package com.ebdz.data.local.di

import com.ebdz.data.local.provider.DaoProvider
import com.ebdz.data.local.provider.DatabaseProvider
import org.koin.dsl.module

/**
 * Local dependency injection module.
 */
val localModule = module {
    // Providers
    single { DatabaseProvider(get()) }
    single { DaoProvider(get()) }
}
