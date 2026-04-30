package ru.easycode.zerotoheroandroidtdd.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.domain.Repository

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository,
) {
    private val job = SupervisorJob()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    val singleLiveDataWrapper = liveDataWrapper

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)

        coroutineScope.launch {
            val response = repository.load()
            liveDataWrapper.update(UiState.ShowData(response.text))
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        val state = bundleWrapper.restore()
        liveDataWrapper.update(state)
    }


    fun onDestroy() {
        coroutineScope.cancel()
    }
}
