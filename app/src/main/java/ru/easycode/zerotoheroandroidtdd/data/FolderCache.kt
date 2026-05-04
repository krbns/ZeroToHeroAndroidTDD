package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderCache(
    @PrimaryKey val id: Long,
    val text: String,
    val notesCount: Int = 0
)
