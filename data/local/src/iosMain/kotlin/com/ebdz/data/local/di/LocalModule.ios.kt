package com.ebdz.data.local.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ebdz.data.local.TaskDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

private const val DB_NAME = "template.db"

/**
 * iOS-side Koin wiring: the Room [TaskDatabase] built on the bundled SQLite driver, stored in
 * the app's documents directory.
 */
fun iosLocalModule(): Module = module {
    single {
        Room.databaseBuilder<TaskDatabase>(name = databasePath())
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.Default)
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun databasePath(): String {
    val documentsDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    val documentsPath = requireNotNull(documentsDirectory?.path) {
        "Unable to resolve iOS documents directory for the local database"
    }
    return "$documentsPath/$DB_NAME"
}
