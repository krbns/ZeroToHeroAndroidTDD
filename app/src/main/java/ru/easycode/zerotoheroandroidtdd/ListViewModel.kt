package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.easycode.zerotoheroandroidtdd.dao.RecordEntity
import ru.easycode.zerotoheroandroidtdd.dao.RecordsDao

class ListViewModel(
    private val dao: RecordsDao,
    private val provideTime: ProvideTime,
    private val runAsync: RunAsync,
) : ViewModel() {

    companion object {
        private const val STATE_KEY = "STATE_KEY"
    }

    private var nextId = 1L

    val state: StateFlow<List<RecordEntity>> = dao.list().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun add(text: String) {
        runAsync.run(scope = viewModelScope, background = {
            dao.insert(
                RecordEntity(
                    id = nextId++,
                    text = text,
                )
            )
        })
    }
}

class ListViewModelFactory(
    private val dao: RecordsDao,
    private val provideTime: ProvideTime,
    private val runAsync: RunAsync,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(
                dao = dao,
                provideTime = provideTime,
                runAsync = runAsync
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}