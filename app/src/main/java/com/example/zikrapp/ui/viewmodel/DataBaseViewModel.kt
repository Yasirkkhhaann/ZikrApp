package com.example.zikrapp.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zikrapp.data.DatabaseInitializer
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.data.ZikrDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataBaseViewModel : ViewModel() {


    val ZikrDao: ZikrDao = DatabaseInitializer.zikrDatabase.getzikrDao()

    val zikrlist: StateFlow<List<Zikr>> = ZikrDao.getAllZikrs().
    stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addZikr(zikr: Zikr) {
        viewModelScope.launch(Dispatchers.IO) {
            ZikrDao.addZikr(zikr)
        }

    }

    suspend fun getZikrById(id: Int): Flow<Zikr?> = withContext(Dispatchers.IO) {
        ZikrDao.getZikrById(id)
    }


    fun deleteZikr(zikrId: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            ZikrDao.deleteZikr(zikrId)
        }
    }

    fun updateZikr(
        zikrId: Int,
        zikrName: String,
        Start: Int,
        End: Int,
        zikrDescription: String
    ) {

        viewModelScope.launch(Dispatchers.IO) {

            ZikrDao.updateZikr(zikrId, zikrName, Start, End, zikrDescription)

        }
    }
}