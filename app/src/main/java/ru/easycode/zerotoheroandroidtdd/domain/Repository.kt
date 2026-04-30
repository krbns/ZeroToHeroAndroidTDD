package ru.easycode.zerotoheroandroidtdd.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.data.SimpleService
import ru.easycode.zerotoheroandroidtdd.data.model.SimpleResponse

interface Repository {

    suspend fun load(): SimpleResponse

    class Base(val service: SimpleService, val url: String) : Repository {
        override suspend fun load() = withContext(Dispatchers.IO) {
            service.fetch(url)
        }
    }
}