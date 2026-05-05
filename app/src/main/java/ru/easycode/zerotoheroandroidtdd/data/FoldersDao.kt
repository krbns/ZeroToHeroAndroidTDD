package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoldersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(folder: FolderCache)

    @Query("DELETE FROM folders WHERE id = :folderId")
    suspend fun delete(folderId: Long)

    @Query("SELECT folders.id, folders.text, COUNT(notes.id) as notesCount FROM folders LEFT JOIN notes ON folders.id = notes.folderId GROUP BY folders.id ORDER BY folders.id")
    suspend fun folders(): List<FolderCache>

}