package ru.easycode.zerotoheroandroidtdd.features.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.features.folders.FolderUi

interface FolderLiveDataWrapper {

    interface Rename {
        fun rename(newName: String)
    }

    interface RenameAndRead: Rename, LiveDataWrapper.Read<FolderUi>

    interface Update {
        fun update(folder: FolderUi)
    }

    interface Decrement : FolderLiveDataWrapper {
        fun decrement()
    }

    interface Increment : FolderLiveDataWrapper {
        fun increment()
    }

    interface Mutable : Update, LiveDataWrapper.Read<FolderUi> {
        fun folderId(): Long
    }

    interface All : RenameAndRead, Mutable, Decrement, Increment

    class Base(val liveData: MutableLiveData<FolderUi> = MutableLiveData()) : All {

        override fun rename(newName: String) {
            liveData.postValue(liveData.value?.copy(title = newName))
        }

        override fun folderId(): Long {
            return liveData.value?.id ?: 0
        }

        override fun update(folder: FolderUi) {
            liveData.value = folder
        }

        override fun decrement() {
            var notesCount = liveData.value?.notesCount ?: 0
            liveData.postValue(liveData.value?.copy(notesCount = --notesCount))
        }

        override fun increment() {
            var notesCount = liveData.value?.notesCount ?: 0
            liveData.postValue(liveData.value?.copy(notesCount = ++notesCount))
        }

        override fun liveData(): LiveData<FolderUi> {
            return liveData
        }
    }
}