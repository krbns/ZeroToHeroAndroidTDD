package ru.easycode.zerotoheroandroidtdd.screens

import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.core.nav.Screen
import ru.easycode.zerotoheroandroidtdd.features.edit.EditFolderFragment
import ru.easycode.zerotoheroandroidtdd.features.edit.EditFolderFragment.Companion.FOLDER_ID_ARG

data class EditFolderScreen(val folderId: Long) : Screen.Replace(EditFolderFragment::class.java) {
    override fun args(): Bundle = Bundle().apply {
        putLong(FOLDER_ID_ARG, folderId)
    }
}
