package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteCache(
    @PrimaryKey val id: Long,
    val text: String,
    val folderId: Long
)
