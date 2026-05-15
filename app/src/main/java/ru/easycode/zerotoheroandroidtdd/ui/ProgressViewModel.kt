package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.StateFlow
import ru.easycode.zerotoheroandroidtdd.RunAsync
import ru.easycode.zerotoheroandroidtdd.data.Repository

class ProgressViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val runAsync: RunAsync,
    private val repository: Repository
) : ViewModel() {

    companion object {
        private const val UI_STATE_KEY = "ui_state_key"
    }

    val state: StateFlow<ProgressUi> = savedStateHandle.getStateFlow(
        key = UI_STATE_KEY,
        initialValue = ProgressUi.Initial
    )

    fun load() {
        savedStateHandle[UI_STATE_KEY] = ProgressUi.Loading
    }

    fun loadInternal() {
        runAsync.runAsync(
            scope = viewModelScope,
            background = { repository.load() },
            ui = { savedStateHandle[UI_STATE_KEY] = ProgressUi.Data(it) }
        )
    }
}

class ProgressViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val runAsync: RunAsync,
    private val repository: Repository
) : AbstractSavedStateViewModelFactory(owner, null) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return ProgressViewModel(handle, runAsync, repository) as T
    }
}