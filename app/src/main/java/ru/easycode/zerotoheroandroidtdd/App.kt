package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.room.Room
import ru.easycode.zerotoheroandroidtdd.dao.AppDatabase

class App : Application() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }
}