package ru.easycode.zerotoheroandroidtdd.features.folders

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface FolderListLiveDataWrapper {

    interface Create : FolderListLiveDataWrapper {
        fun create(folderUi: FolderUi)
    }

    interface UpdateListAndRead : FolderListLiveDataWrapper {
        fun update(list: List<FolderUi>)
    }

    interface Read : LiveDataWrapper.Read<List<FolderUi>>
}