package ru.easycode.zerotoheroandroidtdd.ui

import java.io.Serializable

sealed class ProgressUi : Serializable {
    data object Initial : ProgressUi() {
        private fun readResolve(): Any = Initial
    }

    data class Data(val value: String) : ProgressUi()

    data object Loading : ProgressUi() {
        private fun readResolve(): Any = Loading
    }

}
