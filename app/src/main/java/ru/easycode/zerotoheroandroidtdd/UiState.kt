package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    val text: String
    val decrementBtnEnabled: Boolean
    val incrementBtnEnabled: Boolean

    fun apply(
        countTextView: TextView?,
        incrementButton: Button?,
        decrementButton: Button?
    ) {
        countTextView?.text = text
        incrementButton?.isEnabled = incrementBtnEnabled
        decrementButton?.isEnabled = decrementBtnEnabled
    }

    data class Base(
        override val text: String,
        override val decrementBtnEnabled: Boolean = true,
        override val incrementBtnEnabled: Boolean = true
    ) : UiState

    data class Max(
        override val text: String,
        override val decrementBtnEnabled: Boolean = true,
        override val incrementBtnEnabled: Boolean = false
    ) : UiState

    data class Min(
        override val text: String,
        override val decrementBtnEnabled: Boolean = false,
        override val incrementBtnEnabled: Boolean = true
    ) : UiState
}