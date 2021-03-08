package jp.co.practice.shopinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jp.co.practice.shopinglist.data.db.dao.ShoppingDao
import jp.co.practice.shopinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(mContext: Context) = instance ?: synchronized(LOCK) {
            instance ?: createRoomDatabase(mContext).also { instance = it }
        }

        private fun createRoomDatabase(mContext: Context) =
            Room.databaseBuilder(
                mContext.applicationContext,
                AppDatabase::class.java,
                "AppDataBase"
            ).build()
    }

}