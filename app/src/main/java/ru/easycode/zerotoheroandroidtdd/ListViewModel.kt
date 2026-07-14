package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class ListViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val STATE_KEY = "STATE_KEY"
    }

    val state: StateFlow<List<String>> =
        savedStateHandle.getStateFlow(
            key = STATE_KEY,
            initialValue = emptyList()
        )

    fun add(text: String) {
        val list = savedStateHandle.get<List<String>>(STATE_KEY)?.toMutableList()
        list?.add(0, text)
        savedStateHandle[STATE_KEY] = list
    }
}