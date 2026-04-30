package ru.easycode.zerotoheroandroidtdd.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.LoadResult
import ru.easycode.zerotoheroandroidtdd.data.SimpleService
import java.net.UnknownHostException

interface Repository {

    suspend fun load(): LoadResult

    class Base(val service: SimpleService, val url: String) : Repository {
        override suspend fun load() = withContext(Dispatchers.IO) {
            try {
                LoadResult.Success(service.fetch(url))
            } catch (e: UnknownHostException) {
                LoadResult.Error(noConnection = true)
            } catch (e: Exception) {
                LoadResult.Error(noConnection = false)
            }
        }
    }
}