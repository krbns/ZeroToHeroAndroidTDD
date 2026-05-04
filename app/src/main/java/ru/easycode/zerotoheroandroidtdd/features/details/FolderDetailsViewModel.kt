package ru.easycode.zerotoheroandroidtdd.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.NotesRepository
import ru.easycode.zerotoheroandroidtdd.features.createnote.NoteUi
import ru.easycode.zerotoheroandroidtdd.features.edit.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.screens.CreateNoteScreen
import ru.easycode.zerotoheroandroidtdd.screens.EditFolderScreen
import ru.easycode.zerotoheroandroidtdd.screens.EditNoteScreen
import ru.easycode.zerotoheroandroidtdd.screens.FoldersListScreen

class FolderDetailsViewModel(
    private val noteListRepository: NotesRepository.ReadList,
    private val liveDataWrapper: NoteListLiveDataWrapper.UpdateListAndRead,
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel(), NoteListLiveDataWrapper.Read {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    fun init() {
        val folderId = folderLiveDataWrapper.folderId()
        viewModelScope.launch(dispatcher) {
            val notes = noteListRepository.noteList(folderId)
            liveDataWrapper.update(notes.map { NoteUi(it.id, it.title, it.folderId) })
        }
    }

    fun createNote() {
        navigation.update(CreateNoteScreen(folderLiveDataWrapper.folderId()))
    }

    fun editNote(note: NoteUi) {
        navigation.update(EditNoteScreen(note.id))
    }

    fun comeback() {
        clear.clear(this::class.java)
        navigation.update(FoldersListScreen)
    }

    fun editFolder() {
        navigation.update(EditFolderScreen(folderLiveDataWrapper.folderId()))
    }

    override fun liveData(): LiveData<List<NoteUi>> {
        TODO()
    }

}