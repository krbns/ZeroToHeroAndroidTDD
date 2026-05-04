package ru.easycode.zerotoheroandroidtdd.features.createfolder

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.features.folders.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.features.folders.FolderUi
import ru.easycode.zerotoheroandroidtdd.screens.FoldersListScreen

class CreateFolderViewModel(
    private val repository: FoldersRepository.Create,
    private val liveDataWrapper: FolderListLiveDataWrapper.Create,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    fun createFolder(name: String) {
        viewModelScope.launch(dispatcher) {
            val folderId = repository.createFolder(name)
            liveDataWrapper.create(FolderUi(folderId, name, 0))
        }
        comeback()
    }

    fun comeback() {
        clear.clear(this::class.java)
        navigation.update(FoldersListScreen)
    }
}