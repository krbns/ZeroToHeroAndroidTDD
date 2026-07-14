package ru.easycode.zerotoheroandroidtdd.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String,
)

