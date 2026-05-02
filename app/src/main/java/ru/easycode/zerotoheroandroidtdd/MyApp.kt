package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import ru.easycode.zerotoheroandroidtdd.ui.DefaultListLiveDataWrapper

class MyApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        mainViewModel = MainViewModel(DefaultListLiveDataWrapper())
    }
}