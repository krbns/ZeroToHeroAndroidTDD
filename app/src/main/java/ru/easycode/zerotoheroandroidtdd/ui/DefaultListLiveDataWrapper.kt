package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent

class DefaultListLiveDataWrapper(
    private val data: MutableLiveData<ArrayList<CharSequence>> = SingleLiveEvent()
) : ListLiveDataWrapper {

    override fun liveData(): LiveData<List<CharSequence>> {
        return data.map { it.toList() }
    }

    override fun add(new: CharSequence) {
        val currentList = data.value ?: ArrayList()
        currentList.add(new)
        update(currentList)

    }

    override fun save(bundle: BundleWrapper.Save) {
        bundle.save(data.value ?: ArrayList())
    }

    override fun update(list: List<CharSequence>) {
        data.value = ArrayList(list)
    }
}