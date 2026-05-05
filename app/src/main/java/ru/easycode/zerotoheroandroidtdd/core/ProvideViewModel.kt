package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.data.NotesRepository
import ru.easycode.zerotoheroandroidtdd.features.createfolder.CreateFolderViewModel
import ru.easycode.zerotoheroandroidtdd.features.createnote.CreateNoteViewModel
import ru.easycode.zerotoheroandroidtdd.features.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.features.edit.EditFolderViewModel
import ru.easycode.zerotoheroandroidtdd.features.edit.EditNoteViewModel
import ru.easycode.zerotoheroandroidtdd.features.edit.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.features.edit.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.features.folders.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.features.folders.FolderListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Factory(private val provideViewModel: ProvideViewModel) : ClearViewModels,
        ProvideViewModel {

        private val map = mutableMapOf<Class<out ViewModel>, ViewModel>()

        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            viewModelClasses.forEach {
                map.remove(it)
            }
        }

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            if (map.contains(viewModelClass)) {
                return map[viewModelClass] as T
            } else {
                map[viewModelClass] = provideViewModel.viewModel(viewModelClass)
                return map[viewModelClass] as T
            }
        }
    }

    class Base(
        private val navigation: Navigation.Mutable,
        private val folderLiveDataWrapper: FolderLiveDataWrapper.All,
        private val folderListLiveDataWrapper: FolderListLiveDataWrapper.All,
        private val noteListLiveDataWrapper: NoteListLiveDataWrapper.All,
        private val noteLiveDataWrapper: NoteLiveDataWrapper,
        private val notesRepository: NotesRepository.All,
        private val foldersRepository: FoldersRepository.All,
        private val clearViewModels: ClearViewModels,
    ) : ProvideViewModel {
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(
                    navigation
                ) as T
                CreateNoteViewModel::class.java -> CreateNoteViewModel(
                    folderLiveDataWrapper = folderLiveDataWrapper,
                    addLiveDataWrapper = noteListLiveDataWrapper,
                    repository = notesRepository,
                    navigation = navigation,
                    clear = clearViewModels,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main,
                ) as T

                FolderListViewModel::class.java -> FolderListViewModel(
                    repository = foldersRepository,
                    listLiveDataWrapper = folderListLiveDataWrapper,
                    folderLiveDataWrapper = folderLiveDataWrapper,
                    navigation = navigation,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main,
                ) as T

                CreateFolderViewModel::class.java -> CreateFolderViewModel(
                    repository = foldersRepository,
                    liveDataWrapper = folderListLiveDataWrapper,
                    navigation = navigation,
                    clear = clearViewModels,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main,
                ) as T

                FolderDetailsViewModel::class.java -> FolderDetailsViewModel(
                    noteListRepository = notesRepository,
                    liveDataWrapper = noteListLiveDataWrapper,
                    folderLiveDataWrapper = folderLiveDataWrapper,
                    navigation = navigation,
                    clear = clearViewModels,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main,
                ) as T

                EditFolderViewModel::class.java -> EditFolderViewModel(
                    folderLiveDataWrapper = folderLiveDataWrapper,
                    repository = foldersRepository,
                    navigation = navigation,
                    clear = clearViewModels,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main,
                ) as T

                EditNoteViewModel::class.java -> EditNoteViewModel(
                    folderLiveDataWrapper = folderLiveDataWrapper,
                    noteLiveDataWrapper = noteLiveDataWrapper,
                    noteListLiveDataWrapper = noteListLiveDataWrapper,
                    repository = notesRepository,
                    navigation = navigation,
                    clear = clearViewModels,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main,
                ) as T
                else -> throw IllegalArgumentException("no viewmodel found $viewModelClass")
            }
        }
    }
}