package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData

interface LiveDataWrapper {

    fun update(value: UiState)

    fun liveData(): LiveData<UiState>
}