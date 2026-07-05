package com.ebdz.data.local.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ebdz.data.local.TaskDatabase
import org.koin.dsl.module

private const val DB_NAME = "template.db"

/** Android-side Koin wiring: the Room [TaskDatabase] built against a [Context]. */
fun androidLocalModule() = module {
    single { getDatabaseBuilder(get()).build() }
}

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<TaskDatabase> {
    val dbFile = context.getDatabasePath(DB_NAME)
    return Room.databaseBuilder<TaskDatabase>(context = context, name = dbFile.absolutePath)
        // The local DB is a disposable cache for this skeleton feature, so recreate it on any
        // schema change instead of shipping migrations.
        .fallbackToDestructiveMigration(dropAllTables = true)
}
