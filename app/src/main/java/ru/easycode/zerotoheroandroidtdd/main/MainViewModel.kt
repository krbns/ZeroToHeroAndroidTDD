package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.data.Repository

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel(), LiveDataWrapper.Read<List<ItemUi>> {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    fun init() {
        viewModelScope.launch(dispatcher) {
            val list = repository.list().map { ItemUi(it.id, it.text) }
            liveDataWrapper.update(list)
        }
    }

    override fun liveData(): LiveData<List<ItemUi>> {
        return liveDataWrapper.liveData()
    }
}