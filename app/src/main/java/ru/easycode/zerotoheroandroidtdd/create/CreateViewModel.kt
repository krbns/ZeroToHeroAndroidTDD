package ru.easycode.zerotoheroandroidtdd.create

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.core.nav.Screen

class CreateViewModel(
    private val addLiveDataWrapper: ListLiveDataWrapper.Add,
    private val navigation: Navigation.Update,
    private val clearViewModel: ClearViewModel,
) : ViewModel() {

    fun add(text: String) {
        addLiveDataWrapper.add(text)
        navigation.update(Screen.Pop)
        clearViewModel.clear(this::class.java)
    }

    fun comeback() {
        navigation.update(Screen.Pop)
        clearViewModel.clear(this::class.java)
    }
}