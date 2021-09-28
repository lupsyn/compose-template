package com.ebdz.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SimpleEntity(
    @ColumnInfo(name = "kd") @PrimaryKey(autoGenerate = true) var id: Long = 0
)
