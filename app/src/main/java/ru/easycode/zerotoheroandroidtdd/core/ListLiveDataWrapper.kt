package ru.easycode.zerotoheroandroidtdd.core

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<String>>

    interface Update : LiveDataWrapper.Update<List<String>>
    interface Mutable : Read, Update

    interface Add : ListLiveDataWrapper {
        fun add(value: String)
    }

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<String>>(), All {

        override fun add(value: String) {
            val currentList = liveData.value ?: ArrayList()
            val newList = ArrayList<String>(currentList)
            newList.add(value)
            update(newList)
        }
    }
}