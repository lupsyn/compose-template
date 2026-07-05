package com.ebdz.data.local.di

import com.ebdz.data.local.TaskDatabase
import org.koin.dsl.module

/**
 * Common local dependency injection module. The [TaskDatabase] itself is provided by the
 * platform module (`androidLocalModule`/`iosLocalModule`) since building it requires a
 * platform-specific driver.
 */
val localModule = module {
    single { get<TaskDatabase>().simpleDao() }
}
