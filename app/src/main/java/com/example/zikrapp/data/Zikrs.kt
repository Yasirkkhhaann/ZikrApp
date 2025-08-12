package com.example.zikrapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector


@Entity(tableName = "zikr_table")
data class Zikr(

    @PrimaryKey(autoGenerate = true) val zikrId: Int,
    val zikrName: String,
    val zikrDescription: String,
    val zikrCountStart: Int,
    val zikrCountEnd: Int
) : Flow<Zikr?> {
    override suspend fun collect(collector: FlowCollector<Zikr?>) {
        TODO("Not yet implemented")
    }
}
