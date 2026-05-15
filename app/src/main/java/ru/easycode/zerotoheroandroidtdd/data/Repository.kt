package ru.easycode.zerotoheroandroidtdd.data

import kotlinx.coroutines.delay

interface Repository {

    suspend fun load(): String

    class Base() : Repository {
        override suspend fun load(): String {
            delay(3000)
            return "Success!"
        }
    }
}