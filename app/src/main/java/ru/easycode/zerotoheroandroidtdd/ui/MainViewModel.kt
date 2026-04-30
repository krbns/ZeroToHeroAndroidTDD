package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.domain.Repository

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository,
) : ViewModel() {
    private val job = SupervisorJob()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)
    val liveData = liveDataWrapper

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)

        coroutineScope.launch {
            repository.load()
            liveDataWrapper.update(UiState.ShowData)
        }
    }


    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()

    }
}

class MainViewModelFactory(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(liveDataWrapper, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}