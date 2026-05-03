package ru.easycode.zerotoheroandroidtdd.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.easycode.zerotoheroandroidtdd.data.ItemCache
import ru.easycode.zerotoheroandroidtdd.data.ItemsDao

@Database(entities = [ItemCache::class], version = 1)
abstract class ItemsDataBase: RoomDatabase() {
    abstract fun itemsDao(): ItemsDao

    companion object {
        @Volatile
        private var INSTANCE: ItemsDataBase? = null

        fun getInstance(context: Context): ItemsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemsDataBase::class.java,
                    "items_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}