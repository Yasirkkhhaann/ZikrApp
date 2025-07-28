package com.example.zikrapp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


class ZikrUiStatee(private val context: Context){

    val Context.dataStore by preferencesDataStore(name = "zikrUis")

    val zikrId = context.dataStore.data.map {
        it[zikridd]
    }
    suspend fun updatezikrid(id: Int) =
        context.dataStore.edit { setting ->
            setting[zikridd] = id

        }



    companion object {

        val zikridd = intPreferencesKey("zikridd")

    }
}