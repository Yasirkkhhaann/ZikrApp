package com.example.zikrapp.data

import ZikrDatabase
import android.app.Application
import androidx.room.Room

class DatabaseInitializer : Application()  {


    companion object {
        lateinit var zikrDatabase : ZikrDatabase

    }

    override fun onCreate() {
        super.onCreate()
        zikrDatabase = Room.databaseBuilder(
            applicationContext,
            ZikrDatabase::class.java,
            ZikrDatabase.DATABASE_NAME

        ).build()
    }


}