package ru.easycode.zerotoheroandroidtdd

import ru.easycode.zerotoheroandroidtdd.ui.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.ui.ListLiveDataWrapper

class MainViewModel(private val listLiveDataWrapper: ListLiveDataWrapper) {

    fun liveData() = listLiveDataWrapper.liveData()

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }
}