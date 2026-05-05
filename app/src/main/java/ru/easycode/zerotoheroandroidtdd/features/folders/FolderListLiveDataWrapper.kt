package ru.easycode.zerotoheroandroidtdd.features.folders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

interface FolderListLiveDataWrapper {

    interface Create : FolderListLiveDataWrapper {
        fun create(folderUi: FolderUi)
    }

    interface Read : LiveDataWrapper.Read<List<FolderUi>>

    interface UpdateListAndRead : FolderListLiveDataWrapper, Read {
        fun update(list: List<FolderUi>)
    }

    interface All : Create, Read, UpdateListAndRead

    class Base(val liveData: MutableLiveData<List<FolderUi>> = SingleLiveEvent()) : All {
        override fun create(folderUi: FolderUi) {
            val list = liveData.value?.toMutableList()
            list?.add(folderUi)
            liveData.postValue(list)
        }

        override fun update(list: List<FolderUi>) {
            liveData.postValue(list)
        }

        override fun liveData(): LiveData<List<FolderUi>> {
            return liveData
        }
    }
}