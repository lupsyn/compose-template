package com.ebdz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ebdz.data.local.entity.SimpleEntity

@Dao
interface SimpleDao {

    @Query("SELECT * FROM simple_items")
    suspend fun getAll(): List<SimpleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SimpleEntity): Long

    @Query("DELETE FROM simple_items")
    suspend fun deleteAll()
}
