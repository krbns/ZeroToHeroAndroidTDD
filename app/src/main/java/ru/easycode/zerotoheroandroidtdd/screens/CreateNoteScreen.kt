package ru.easycode.zerotoheroandroidtdd.screens

import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.core.nav.Screen
import ru.easycode.zerotoheroandroidtdd.features.createnote.CreateNoteFragment
import ru.easycode.zerotoheroandroidtdd.features.createnote.CreateNoteFragment.Companion.FOLDER_ID_ARG

data class CreateNoteScreen(val folderId: Long) : Screen.Replace(CreateNoteFragment::class.java) {
    override fun args(): Bundle = Bundle().apply {
        putLong(FOLDER_ID_ARG, folderId)
    }
}
