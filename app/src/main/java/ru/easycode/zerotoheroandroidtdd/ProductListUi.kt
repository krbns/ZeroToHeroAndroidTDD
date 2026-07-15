package ru.easycode.zerotoheroandroidtdd

sealed interface ProductListUi {
    data class Base(
        val id: Int,
        val name: String,
        val price: String,
        val os: String,
        val ram: Int,
    ) : ProductListUi

    object Empty : ProductListUi
}