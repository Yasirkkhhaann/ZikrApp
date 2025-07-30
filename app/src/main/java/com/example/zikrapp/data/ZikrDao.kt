package com.example.zikrapp.data



import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ZikrDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addZikr(zikr: Zikr)

    @Query("SELECT * FROM zikr_table WHERE zikrId = :id")
    fun getZikrById(id: Int): Flow<Zikr?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addtest(id: ZikrStateee)
    @Query("Update zikr_state set lastZikrId = :id where id = 1")
    fun setLastZikr(id: Int)

    @Query("SELECT lastZikrId FROM zikr_state where id = 1 limit 1")
    fun getLastZikrId(): Flow<Int?>

    @Query("SELECT * FROM zikr_table")
    fun getAllZikrs(): Flow<List<Zikr>>

    @Query("UPDATE zikr_table SET zikrCountStart = :count where zikrId = :id")
    fun updatebyidcount(id: Int,count:Int)
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
