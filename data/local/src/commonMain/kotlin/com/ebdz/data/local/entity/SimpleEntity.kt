package com.ebdz.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Placeholder entity for the template's persistence layer.
 */
@Entity(tableName = "simple_items")
data class SimpleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "label") val label: String
)
