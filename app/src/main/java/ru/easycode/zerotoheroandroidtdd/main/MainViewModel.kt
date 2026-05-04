package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.core.nav.Screen
import ru.easycode.zerotoheroandroidtdd.screens.FoldersListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable,
) : ViewModel(), Navigation.Read {

    fun init(firstRun: Boolean) {
        navigation.update(FoldersListScreen)
    }

    override fun liveData(): LiveData<Screen> {
        return navigation.liveData()
    }
}