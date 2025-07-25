package com.example.zikrapp.data



import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ZikrDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addZikr(zikr: Zikr)

    @Query("SELECT * FROM zikr_table WHERE zikrId = :id")
    fun getZikrById(id: Int): Zikr?

    @Query("SELECT * FROM zikr_table")
    fun getAllZikrs(): LiveData<List<Zikr>>

    @Query("DELETE FROM zikr_table WHERE zikrId = :zikrId")
    fun deleteZikr(zikrId: Int)

    @Query("""
    UPDATE zikr_table 
    SET zikrName = :zikrName, 
        zikrCountStart = :Start, 
        zikrCountEnd = :End, 
        zikrDescription = :zikrDescription 
    WHERE zikrId = :zikrId
""")
    suspend fun updateZikr(
        zikrId: Int,
        zikrName: String,
        Start: Int,
        End: Int,
        zikrDescription: String
    )
}
