package ru.easycode.zerotoheroandroidtdd.main

data class ItemUi(
    val id: Long,
    val text: String
) {
    fun areItemsSame(item: ItemUi) = this.id == item.id
}
