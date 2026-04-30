package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DefaultLiveDataWrapper : LiveDataWrapper {
    private val liveData = MutableLiveData<UiState>()

    override fun update(value: UiState) {
        liveData.postValue(value)
    }

    override fun liveData(): LiveData<UiState> {
        return liveData
    }
}