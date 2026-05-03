package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.main.ItemUi

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<ItemUi>>

    interface Update : LiveDataWrapper.Update<List<ItemUi>>

    interface Delete : ListLiveDataWrapper {
        fun delete(item: ItemUi)
    }

    interface Add : ListLiveDataWrapper {
        fun add(value: ItemUi)
    }

    interface Mutable : Read, Update, Delete

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<ItemUi>>(), All {

        override fun add(value: ItemUi) {
            val currentList = liveData.value ?: ArrayList()
            val newList = ArrayList<ItemUi>(currentList)
            newList.add(value)
            update(newList)
        }

        override fun delete(item: ItemUi) {
            val currentList = liveData.value ?: ArrayList()
            val newList = ArrayList<ItemUi>(currentList)
            newList.remove(item)
            update(newList)
        }
    }
}