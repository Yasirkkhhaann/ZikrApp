//package com.example.zikrapp.data
//
//import android.content.Context
//import androidx.datastore.preferences.core.stringPreferencesKey
//import kotlinx.coroutines.flow.Flow
//
//val LAST_ZIKR_ID_KEY = stringPreferencesKey("last_zikr_id")
//
//class ZikrRepository(context: Context) {
//    private val dataStore = context.dataStore // extend context with preferencesDataStore
//
//    val lastZikrIdFlow: Flow<String?> = dataStore.data.map { prefs ->
//        prefs[LAST_ZIKR_ID_KEY]
//    }
//
//    suspend fun saveLastZikrId(zikrId: String) {
//        dataStore.edit { prefs ->
//            prefs[LAST_ZIKR_ID_KEY] = zikrId
//        }
//    }
//}
