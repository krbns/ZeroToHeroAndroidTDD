package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData

interface LiveDataWrapper {

    fun liveData(): LiveData<UiState>

    interface Save : LiveDataWrapper {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Update : LiveDataWrapper {
        fun update(value: UiState)
    }

    interface Mutable : Save, Update
}