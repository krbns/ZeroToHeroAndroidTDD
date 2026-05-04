package ru.easycode.zerotoheroandroidtdd.features.edit

import ru.easycode.zerotoheroandroidtdd.features.folders.FolderUi

interface FolderLiveDataWrapper {

    interface Rename {
        fun rename(newName: String)
    }

    interface Update {
        fun update(folder: FolderUi)
    }

    interface Decrement : FolderLiveDataWrapper {
        fun decrement()
    }

    interface Increment : FolderLiveDataWrapper {
        fun increment()
    }

    interface Mutable : Update {
        fun folderId(): Long
    }

}