package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Bundle

class DefaultBundleWrapper(
    private val bundle: Bundle
) : BundleWrapper.Mutable {

    override fun save(uiState: UiState) {
        val value = when (uiState) {
            UiState.ShowProgress -> "progress"
            UiState.ShowData -> "data"
        }
        bundle.putString(KEY, value)
    }

    override fun restore(): UiState {
        return when (bundle.getString(KEY)) {
            "data" -> UiState.ShowData
            else -> UiState.ShowProgress
        }
    }

    private companion object {
        const val KEY = "ui_state_key"
    }
}
