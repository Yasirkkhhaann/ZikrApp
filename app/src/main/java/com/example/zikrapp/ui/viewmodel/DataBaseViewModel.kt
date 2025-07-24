package com.example.zikrapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.zikrapp.data.DatabaseInitializer
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.data.ZikrDao

class DataBaseViewModel : ViewModel()  {

    val ZikrDao: ZikrDao = DatabaseInitializer.zikrDatabase.getzikrDao()

    val zikrlist : LiveData<List<Zikr>> = ZikrDao.getAllZikrs()

    fun addZikr(zikr: Zikr) {
        ZikrDao.addZikr(zikr)
    }

    fun getZikrById(id: Int): Zikr? {
        return ZikrDao.getZikrById(id)
    }

    fun deleteZikr(zikrId: Int) {
        ZikrDao.deleteZikr(zikrId)
    }


}