package ru.easycode.zerotoheroandroidtdd.screens

import ru.easycode.zerotoheroandroidtdd.core.nav.Screen
import ru.easycode.zerotoheroandroidtdd.features.createnote.CreateNoteFragment

data class CreateNoteScreen(val folderId: Long) : Screen.Replace(CreateNoteFragment::class.java)
