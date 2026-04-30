package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent

class DefaultLiveDataWrapper : LiveDataWrapper {
    private val singleLiveEvent = SingleLiveEvent<UiState>()

    override fun update(value: UiState) {
        singleLiveEvent.setValue(value)
    }

    override fun save(bundleWrapper: BundleWrapper.Save) {
        bundleWrapper.save(singleLiveEvent.value ?: UiState.ShowProgress)
    }

    override fun liveData(): LiveData<UiState> {
        return singleLiveEvent
    }
}