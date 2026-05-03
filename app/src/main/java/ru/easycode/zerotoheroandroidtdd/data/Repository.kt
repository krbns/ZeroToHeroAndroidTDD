package ru.easycode.zerotoheroandroidtdd.data


interface Repository {

    interface Read: Repository {
        fun list(): List<String>
    }

    interface Add: Repository {
        fun add(value: String)
    }

    interface Mutable: Read, Add

    class Base(private val dataSource: ItemsDao, private val now: Now): Mutable {
        override fun list(): List<String> {
            return dataSource.list().map { it.text }
        }

        override fun add(value: String) {
            dataSource.add(ItemCache(id = now.nowMillis(), text = value))
        }
    }
}