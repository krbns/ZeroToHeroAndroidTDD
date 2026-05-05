package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.features.createnote.NoteUi

interface NoteListLiveDataWrapper {

    interface Update {
        fun update(noteId: Long, newText: String)
    }

    interface UpdateListAndRead : LiveDataWrapper.Mutable<List<NoteUi>>

    interface Create {
        fun create(noteUi: NoteUi)
    }

    interface All : UpdateListAndRead, Create, Update

    class Base(val liveData: MutableLiveData<List<NoteUi>> = SingleLiveEvent()) : All {
        override fun update(value: List<NoteUi>) {
            liveData.postValue(value)
        }

        override fun update(noteId: Long, newText: String) {
            val list = liveData.value?.toMutableList()
            val oldNote = list?.find { it.id == noteId } ?: return
            list.remove(oldNote)
            list.add(oldNote.copy(title = newText))
            update(list)
        }

        override fun create(noteUi: NoteUi) {
            val list = liveData.value?.toMutableList() ?: return
            list.add(noteUi)
            update(list)
        }

        override fun liveData(): LiveData<List<NoteUi>> {
            return liveData
        }
    }
}