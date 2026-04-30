package ru.easycode.zerotoheroandroidtdd.data.model

import com.google.gson.annotations.SerializedName

data class SimpleResponse(
    @SerializedName("text") val text: String
)