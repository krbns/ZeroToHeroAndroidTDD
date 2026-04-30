package ru.easycode.zerotoheroandroidtdd.ui

sealed interface UiState {
    data object ShowProgress : UiState
    data class ShowData(val text: String) : UiState
}
