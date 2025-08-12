package com.example.zikrapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zikr_state")
data class ZikrStateee(
    @PrimaryKey val id: Int,       // fixed row ID for the single state row
    val lastZikrId: Int,
    val isSpeakerOn: Int,
    val isVibrationOn: Int,
    val notSaveCount: Int
)
