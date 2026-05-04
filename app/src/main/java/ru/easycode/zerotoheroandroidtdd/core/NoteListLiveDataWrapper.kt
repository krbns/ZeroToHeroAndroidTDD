package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.features.createnote.NoteUi

interface NoteListLiveDataWrapper {

    interface Update {
        fun update(noteId: Long, newText: String)
    }

    interface UpdateListAndRead : LiveDataWrapper.Update<List<NoteUi>>

    interface Read : LiveDataWrapper.Read<List<NoteUi>>

    interface Create {
        fun create(noteUi: NoteUi)
    }
}