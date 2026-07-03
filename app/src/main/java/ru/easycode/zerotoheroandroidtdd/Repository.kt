package ru.easycode.zerotoheroandroidtdd

interface Repository {
    suspend fun load(): String

    class Base() : Repository {
        override suspend fun load(): String {
            return "Fake Data"
        }
    }
}