package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes WHERE folderId = :folderId")
    suspend fun notes(folderId: Long): List<NoteCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteCache)

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun note(noteId: Long): NoteCache

    @Query("DELETE FROM notes WHERE folderId = :noteId")
    suspend fun delete(noteId: Long)

    @Query("DELETE FROM notes WHERE id = :folderId")
    suspend fun deleteByFolderId(folderId: Long)
}


