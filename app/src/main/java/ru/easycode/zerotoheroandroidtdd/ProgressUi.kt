package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

sealed class ProgressUi : Serializable {
    data object Connected : ProgressUi()
    data object Disconnected : ProgressUi()
    data object Loading : ProgressUi()
    data class Data(val value: String) : ProgressUi()
    data object Empty : ProgressUi()
}