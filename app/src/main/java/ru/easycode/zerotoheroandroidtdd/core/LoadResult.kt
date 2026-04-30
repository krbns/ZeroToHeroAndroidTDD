package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.data.model.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.ui.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.ui.UiState

sealed interface LoadResult {

    fun show(updateLiveData: LiveDataWrapper.Update)
    data class Success(val data: SimpleResponse) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.ShowData(data.text))
        }
    }

    data class Error(val noConnection: Boolean) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            if (noConnection) {
                updateLiveData.update(UiState.ShowData(text = "No internet connection"))
            } else {
                updateLiveData.update(UiState.ShowData(text = "Something went wrong"))
            }
        }
    }
}