package ru.easycode.zerotoheroandroidtdd.features.createnote

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.NotesRepository
import ru.easycode.zerotoheroandroidtdd.features.edit.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.features.edit.FolderLiveDataWrapper

class CreateNoteViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Increment,
    private val addLiveDataWrapper: NoteListLiveDataWrapper.Create,
    private val repository: NotesRepository.Create,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    fun createNote(folderId: Long, text: String) {
        viewModelScope.launch(dispatcher) {
            val noteId = repository.createNote(folderId, text)
            folderLiveDataWrapper.increment()
            addLiveDataWrapper.create(NoteUi(noteId, text, folderId))
        }
        comeback()
    }

    fun comeback() {
        clear.clear(this::class.java)
        navigation.update(FolderDetailsScreen)
    }
}