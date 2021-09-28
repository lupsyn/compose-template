package com.ebdz.local.di

import com.ebdz.local.provider.DaoProvider
import com.ebdz.local.provider.DatabaseProvider
import org.koin.dsl.module

/**
 * Local dependency injection module.
 */
val localModule = module {
    // Providers
    single { DatabaseProvider(get()) }
    single { DaoProvider(get()) }
}
