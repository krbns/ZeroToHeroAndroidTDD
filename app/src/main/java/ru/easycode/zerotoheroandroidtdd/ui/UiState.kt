package ru.easycode.zerotoheroandroidtdd.ui

sealed interface UiState {
    data object ShowProgress : UiState
    data object ShowData : UiState
}
