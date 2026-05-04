package ru.easycode.zerotoheroandroidtdd.features.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.features.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.screens.FoldersListScreen

class EditFolderViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Rename,
    private val repository: FoldersRepository.Edit,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    fun renameFolder(folderId: Long, newName: String) {
        viewModelScope.launch(dispatcher) {
            repository.rename(folderId, newName)
            folderLiveDataWrapper.rename(newName)
        }
        comeback()
    }

    fun deleteFolder(folderId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.delete(folderId)
        }
        clear.clear(this::class.java, FolderDetailsViewModel::class.java)
        navigation.update(FoldersListScreen)
    }

    fun comeback() {
        clear.clear(this::class.java)
        navigation.update(FolderDetailsScreen)
    }
}