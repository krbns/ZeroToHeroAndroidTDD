package ru.easycode.zerotoheroandroidtdd.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.main.ItemUi

class DetailsViewModel(
    private val deleteLiveDataWrapper: ListLiveDataWrapper.Delete,
    private val repository: Repository.Change,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData

    fun init(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            _liveData.postValue(repository.item(itemId).text)
        }
    }

    fun delete(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            val item = repository.item(itemId)
            repository.delete(itemId)
            deleteLiveDataWrapper.delete(ItemUi(item.id, item.text))
        }

        comeback()
    }

    fun update(itemId: Long, newText: String) {
        viewModelScope.launch(dispatcher) {
            repository.update(itemId, newText)
            deleteLiveDataWrapper.update(ItemUi(itemId, newText))
        }
        comeback()
    }

    fun comeback() {
        clear.clearViewModel(this::class.java)
    }
}