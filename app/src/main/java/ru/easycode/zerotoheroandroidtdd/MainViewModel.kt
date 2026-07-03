package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.StateFlow
import ru.easycode.MonitorConnection

class MainViewModel(
    private val runAsync: RunAsync,
    private val repository: Repository,
    private val connection: MonitorConnection,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val stateFlow: StateFlow<ProgressUi> =
        savedStateHandle.getStateFlow(
            key = STATE_KEY,
            initialValue = ProgressUi.Empty
        )

    private var previousConnected = true

    init {
        runAsync.runFlow(
            scope = viewModelScope,
            flow = connection.connectedFlow()
        ) { connected ->

            update(
                alreadyConnected = previousConnected,
                connected = connected
            )

            previousConnected = connected
        }
    }

    fun update(alreadyConnected: Boolean, connected: Boolean) {
//        if (alreadyConnected == connected) return

        val current = stateFlow.value
        if (current is ProgressUi.Loading || current is ProgressUi.Data) return

        savedStateHandle[STATE_KEY] =
            if (connected) ProgressUi.Connected
            else ProgressUi.Disconnected
    }

    fun initial(connected: Boolean) {
        savedStateHandle[STATE_KEY] =
            if (connected)
                ProgressUi.Connected
            else
                ProgressUi.Disconnected
    }

    fun load() {
        savedStateHandle[STATE_KEY] = ProgressUi.Loading
    }

    fun loadInternal() {
        runAsync.runAsync(
            scope = viewModelScope,
            background = { repository.load() },
            ui = { savedStateHandle[STATE_KEY] = ProgressUi.Data(it) }
        )
    }

    companion object {
        private const val STATE_KEY = "state"
    }
}

class MainViewModelFactory(
    private val runAsync: RunAsync,
    private val repository: Repository,
    private val connection: MonitorConnection,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return MainViewModel(
            runAsync = runAsync,
            repository = repository,
            connection = connection,
            savedStateHandle = handle
        ) as T
    }
}