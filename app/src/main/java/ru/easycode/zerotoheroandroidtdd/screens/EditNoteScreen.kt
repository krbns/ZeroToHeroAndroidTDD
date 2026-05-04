package ru.easycode.zerotoheroandroidtdd.screens

import ru.easycode.zerotoheroandroidtdd.core.nav.Screen
import ru.easycode.zerotoheroandroidtdd.features.edit.EditNoteFragment

data class EditNoteScreen(val noteId: Long) : Screen.Replace(EditNoteFragment::class.java)
