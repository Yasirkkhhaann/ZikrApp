package com.example.zikrapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "zikr_table")
data class Zikr(

    @PrimaryKey(autoGenerate = true) val zikrId: Int = 0,
    val zikrName: String,
    val zikrDescription: String,
    val zikrCountStart: Int,
    val zikrCountEnd: Int
)
