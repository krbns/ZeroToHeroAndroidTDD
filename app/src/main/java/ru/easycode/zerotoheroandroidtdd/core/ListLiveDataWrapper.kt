package ru.easycode.zerotoheroandroidtdd.core

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<CharSequence>>

    interface Update : LiveDataWrapper.Update<List<CharSequence>>
    interface Mutable : Read, Update {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Add : ListLiveDataWrapper {
        fun add(source: CharSequence)
    }

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<CharSequence>>(), All {

        override fun add(source: CharSequence) {
            val currentList = liveData.value ?: ArrayList()
            val newList = ArrayList<CharSequence>(currentList)
            newList.add(source)
            update(newList)
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let {
                bundleWrapper.save(ArrayList(it))
            }
        }
    }
}