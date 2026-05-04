package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.features.createnote.Folder

interface FoldersRepository {

    interface ReadList {
        suspend fun folders(): List<Folder>
    }

    interface Create {
        suspend fun createFolder(name: String): Long
    }

    interface Edit {
        suspend fun rename(folderId: Long, newName: String)
        suspend fun delete(folderId: Long)
    }

    class Base(
        private val now: Now,
        private val foldersDao: FoldersDao,
        private val notesDao: NotesDao,
    ) : FoldersRepository, ReadList, Create, Edit {

        override suspend fun delete(folderId: Long) {
            foldersDao.delete(folderId)
            notesDao.deleteByFolderId(folderId)
        }

        override suspend fun rename(folderId: Long, newName: String) {
            val folders = foldersDao.folders()
            val folderCache = folders.find { it.id == folderId }
            folderCache?.let {
                foldersDao.insert(folderCache.copy(text = newName))
            }
        }

        override suspend fun folders(): List<Folder> {
            return foldersDao.folders().map { Folder(it.id, it.text, it.notesCount) }
        }

        override suspend fun createFolder(name: String): Long {
            val folderId = now.timeInMillis()
            foldersDao.insert(FolderCache(folderId, name, 0))
            return folderId
        }
    }
}