package com.example.zikrapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.data.ZikrList






class ZikrDataModel : ViewModel() {
    private val _zikrs = mutableStateListOf<Zikr>()
    val zikrs: List<Zikr> get() = _zikrs



    init {
        _zikrs.addAll(ZikrList)
    }



    fun loadCurrentZikr(zikrId: Int): Zikr? {

        val zikr =  zikrs.find { it.zikrId == zikrId }
        Log.d("ZikrDataModel", "Loaded Zikr: $zikr")
        return zikr
    }

    fun getAllZikr(){

    }






    fun addZikr(name: String, start: Int, end: Int, description: String) {
        val newId = (_zikrs.maxOfOrNull { it.zikrId } ?: 0) + 1
        _zikrs.add(
            Zikr(
                zikrId = newId,
                zikrName = name,
                zikrDescription = description,
                zikrCountStart = start,
                zikrCountEnd = end
            )
        )


    }





        fun updateZikr(id: Int, name: String, start: Int, end: Int, description: String) {
            val index = _zikrs.indexOfFirst { it.zikrId == id }
            if (index != -1) {
                _zikrs[index] = Zikr(
                    zikrId = id,
                    zikrName = name,
                    zikrDescription = description,
                    zikrCountStart = start,
                    zikrCountEnd = end
                )
            }
        }

    fun deleteZikr(id: Int) {
        val index = _zikrs.indexOfFirst { it.zikrId == id }
        if (index != -1) {
            _zikrs.removeAt(index)
        }
    }




}
