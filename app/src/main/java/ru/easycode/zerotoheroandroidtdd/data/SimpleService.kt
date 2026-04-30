package ru.easycode.zerotoheroandroidtdd.data

import retrofit2.http.GET
import retrofit2.http.Url
import ru.easycode.zerotoheroandroidtdd.data.model.SimpleResponse

interface SimpleService {

    @GET
    suspend fun fetch(@Url url: String): SimpleResponse
}