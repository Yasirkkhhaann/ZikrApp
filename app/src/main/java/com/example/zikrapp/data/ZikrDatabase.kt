import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.data.ZikrDao
import com.example.zikrapp.data.ZikrStateee





@Database(entities = [Zikr::class, ZikrStateee::class], version = 1)
abstract class ZikrDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "zikr_database"
    }

    abstract fun getzikrDao(): ZikrDao
}
