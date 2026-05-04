package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.features.createnote.MyNote

interface NotesRepository {
    interface Create : NotesRepository {
        suspend fun createNote(folderId: Long, text: String): Long
    }

    interface ReadList {
        suspend fun noteList(folderId: Long): List<MyNote>

    }

    interface Edit : NotesRepository {
        suspend fun deleteNote(noteId: Long)

        suspend fun renameNote(noteId: Long, newName: String)

        suspend fun note(noteId: Long): MyNote
    }

    class Base(
        private val now: Now,
        private val dao: NotesDao
    ) : Edit, Create, ReadList {

        override suspend fun deleteNote(noteId: Long) {
            dao.delete(noteId)
        }

        override suspend fun renameNote(noteId: Long, newName: String) {
            val note = dao.note(noteId)
            dao.insert(note.copy(text = newName))
        }

        override suspend fun note(noteId: Long): MyNote {
            val cache = dao.note(noteId)
            return MyNote(cache.id, cache.text, cache.folderId)
        }

        override suspend fun createNote(folderId: Long, text: String): Long {
            val noteId = now.timeInMillis()
            dao.insert(NoteCache(noteId, text, folderId))
            return noteId
        }

        override suspend fun noteList(folderId: Long): List<MyNote> {
            return dao.notes(folderId).map { MyNote(it.id, it.text, it.folderId) }
        }
    }
}