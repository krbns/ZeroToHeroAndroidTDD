package ru.easycode.zerotoheroandroidtdd.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.easycode.zerotoheroandroidtdd.data.FolderCache
import ru.easycode.zerotoheroandroidtdd.data.FoldersDao
import ru.easycode.zerotoheroandroidtdd.data.NoteCache
import ru.easycode.zerotoheroandroidtdd.data.NotesDao

@Database(entities = [NoteCache::class, FolderCache::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    abstract fun foldersDao(): FoldersDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "items_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}