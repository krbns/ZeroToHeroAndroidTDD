package ru.easycode.zerotoheroandroidtdd

interface UiState {

    data class Base(val text: String): UiState
    data class Max(val text: String): UiState
}