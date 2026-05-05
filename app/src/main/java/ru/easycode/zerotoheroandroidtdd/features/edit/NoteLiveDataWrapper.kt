package ru.easycode.zerotoheroandroidtdd.features.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

interface NoteLiveDataWrapper: LiveDataWrapper.Read<String> {

    fun update(noteText: String)

    class Base(val liveData: MutableLiveData<String> = SingleLiveEvent()) : NoteLiveDataWrapper {
        override fun update(noteText: String) {
            liveData.postValue(noteText)
        }

        override fun liveData(): LiveData<String> {
            return liveData
        }
    }
}