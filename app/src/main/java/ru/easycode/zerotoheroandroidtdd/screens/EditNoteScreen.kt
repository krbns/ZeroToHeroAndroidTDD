package ru.easycode.zerotoheroandroidtdd.screens

import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.core.nav.Screen
import ru.easycode.zerotoheroandroidtdd.features.edit.EditNoteFragment
import ru.easycode.zerotoheroandroidtdd.features.edit.EditNoteFragment.Companion.NOTE_ID_ARG

data class EditNoteScreen(val noteId: Long) : Screen.Replace(EditNoteFragment::class.java) {
    override fun args(): Bundle = Bundle().apply {
        putLong(NOTE_ID_ARG, noteId)
    }
}
