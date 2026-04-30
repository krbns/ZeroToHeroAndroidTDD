package ru.easycode.zerotoheroandroidtdd.data

import kotlinx.coroutines.delay
import ru.easycode.zerotoheroandroidtdd.domain.Repository

class DefaultRepository : Repository {
    override suspend fun load() {
        delay(3500)
    }
}