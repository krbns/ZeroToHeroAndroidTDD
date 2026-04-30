package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Bundle

class DefaultBundleWrapper(
    private val bundle: Bundle
) : BundleWrapper.Mutable {

    override fun save(uiState: UiState) {
        when (uiState) {
            UiState.ShowProgress -> {
                bundle.putString(TYPE, "progress")
            }

            is UiState.ShowData -> {
                bundle.putString(TYPE, "data")
                bundle.putString(DATA, uiState.text)
            }
        }
    }

    override fun restore(): UiState {
        return when (bundle.getString(TYPE)) {

            "data" -> {
                val text = bundle.getString(DATA).orEmpty()
                UiState.ShowData(text)
            }

            "progress" -> UiState.ShowProgress

            else -> UiState.ShowProgress
        }
    }

    private companion object {
        const val TYPE = "ui_state_type"
        const val DATA = "ui_state_data"
    }
}