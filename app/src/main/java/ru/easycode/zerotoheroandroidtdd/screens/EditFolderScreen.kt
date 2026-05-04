package ru.easycode.zerotoheroandroidtdd.screens

import ru.easycode.zerotoheroandroidtdd.core.nav.Screen
import ru.easycode.zerotoheroandroidtdd.features.edit.EditFolderFragment

data class EditFolderScreen(val folderId: Long) : Screen.Replace(EditFolderFragment::class.java)
