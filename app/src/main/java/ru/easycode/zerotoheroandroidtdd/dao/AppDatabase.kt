package ru.easycode.zerotoheroandroidtdd.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RecordEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): RecordsDao
}