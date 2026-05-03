package ru.easycode.zerotoheroandroidtdd.delete

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

class DeleteViewModel(
    private val deleteLiveDataWrapper: ListLiveDataWrapper.Delete,
    private val repository: Repository.Delete,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    private val _liveDate = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveDate

    fun init(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            _liveDate.postValue(repository.item(itemId).text)
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

    fun comeback() {
        clear.clearViewModel(this::class.java)
    }
}