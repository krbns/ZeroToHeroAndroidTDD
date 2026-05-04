package ru.easycode.zerotoheroandroidtdd.features.folders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.features.edit.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.features.edit.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.screens.CreateFolderScreen

class FolderListViewModel(
    private val repository: FoldersRepository.ReadList,
    private val listLiveDataWrapper: FolderListLiveDataWrapper.UpdateListAndRead,
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Update,
    private val navigation: Navigation.Update,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher
) : ViewModel(), FolderListLiveDataWrapper.Read {

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatcherMain)

    fun init() {
        viewModelScope.launch(dispatcher) {
            val list = repository.folders()
            listLiveDataWrapper.update(list.map { FolderUi(it.id, it.title, it.notesCount) })
        }
    }

    fun addFolder() {
        navigation.update(CreateFolderScreen)
    }

    fun folderDetails(folderUi: FolderUi) {
        folderLiveDataWrapper.update(folderUi)
        navigation.update(FolderDetailsScreen)
    }

    override fun liveData(): LiveData<List<FolderUi>> {
        TODO()
//        return listLiveDataWrapper.liveData()
    }
}