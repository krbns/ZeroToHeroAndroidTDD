package ru.easycode.zerotoheroandroidtdd.features.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.NotesRepository

class EditNoteViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Decrement,
    private val noteLiveDataWrapper: NoteLiveDataWrapper,
    private val noteListLiveDataWrapper: NoteListLiveDataWrapper.Update,
    private val repository: NotesRepository.Edit,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    fun init(noteId: Long) {
        viewModelScope.launch(dispatcher) {
            val note = repository.note(noteId)
            noteLiveDataWrapper.update(note.title)
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.deleteNote(noteId)
            folderLiveDataWrapper.decrement()
        }
        comeback()
    }

    fun renameNote(noteId: Long, newText: String) {
        viewModelScope.launch(dispatcher) {
            repository.renameNote(noteId, newText)
        }
        noteListLiveDataWrapper.update(noteId, newText)
        comeback()
    }

    fun comeback() {
        clear.clear(this::class.java)
        navigation.update(FolderDetailsScreen)
    }
}