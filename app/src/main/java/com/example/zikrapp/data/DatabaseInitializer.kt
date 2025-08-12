package com.example.zikrapp.data

import ZikrDatabase
import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

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

        ).createFromAsset("database/zikrtest1.db").build()
    }


}


//.addMigrations(MIGRATION_2_3)
//val MIGRATION_2_3 = object : Migration(2, 3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "CREATE TABLE IF NOT EXISTS `zikr_state` (`id` INTEGER NOT NULL, `lastZikrId` INTEGER NOT NULL, PRIMARY KEY(`id`))"
//        )
//    }
//}