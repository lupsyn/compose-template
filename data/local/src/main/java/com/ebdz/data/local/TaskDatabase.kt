package com.ebdz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ebdz.data.local.model.SimpleEntity

/**
 * Database class.
 */
@Database(entities = [SimpleEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase()
