package com.ebdz.local.provider

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ebdz.local.TaskDatabase

/**
 * Repository with the [Room] database.
 */
class DatabaseProvider(private val context: Context) {

    private var database: TaskDatabase? = null

    fun getInstance(): TaskDatabase =
        database ?: synchronized(this) {
            database ?: buildDatabase().also { database = it }
        }

    private fun buildDatabase(): TaskDatabase =
        Room.databaseBuilder(context, TaskDatabase::class.java, DB_NAME)
            .addCallback(onCreateDatabase())
            .build()

    @Suppress("GlobalCoroutineUsage")
    private fun onCreateDatabase() = object : RoomDatabase.Callback() {}

    companion object {
        const val DB_NAME = "todo-db"
    }
}
