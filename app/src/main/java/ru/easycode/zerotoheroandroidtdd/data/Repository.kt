package ru.easycode.zerotoheroandroidtdd.data

interface Repository {

    interface Read : Repository {
        fun list(): List<Item>
    }


    interface Add : Repository {
        fun add(value: String): Long
    }

    interface Change : Repository {
        fun item(id: Long): Item

        fun delete(id: Long)

        fun update(id: Long, newText: String)
    }

    interface Mutable : Read, Add, Change

    interface All : Mutable

    class Base(private val dataSource: ItemsDao, private val now: Now) : All {
        override fun item(id: Long): Item {
            val itemCache = dataSource.item(id)
            return Item(itemCache.id, itemCache.text)
        }

        override fun list(): List<Item> {
            return dataSource.list().map { Item(it.id, it.text) }
        }

        override fun add(value: String): Long {
            val millis = now.nowMillis()
            dataSource.add(ItemCache(id = millis, text = value))
            return millis
        }

        override fun delete(id: Long) {
            dataSource.delete(id)
        }

        override fun update(id: Long, newText: String) {
            val item = dataSource.item(id)
            val newItem = item.copy(text = newText)
            dataSource.add(ItemCache(newItem.id, newItem.text))
        }
    }
}

data class Item(val id: Long, val text: String)