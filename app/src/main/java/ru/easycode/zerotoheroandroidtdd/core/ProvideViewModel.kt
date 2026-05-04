package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.data.NotesRepository
import ru.easycode.zerotoheroandroidtdd.features.createnote.CreateNoteViewModel
import ru.easycode.zerotoheroandroidtdd.features.edit.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.features.folders.FolderListLiveDataWrapper

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
        private val folderLiveDataWrapper: FolderLiveDataWrapper.Increment,
        private val folderListLiveDataWrapper: FolderListLiveDataWrapper.UpdateListAndRead,
        private val notesRepository: NotesRepository.Create,
        private val foldersRepository: FoldersRepository.ReadList,
        private val clearViewModels: ClearViewModels,
        private val addLiveDataWrapper: NoteListLiveDataWrapper.Create
    ) : ProvideViewModel {
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                CreateNoteViewModel::class.java -> CreateNoteViewModel(
                    folderLiveDataWrapper = folderLiveDataWrapper,
                    addLiveDataWrapper = addLiveDataWrapper,
                    repository = notesRepository,
                    navigation = navigation,
                    clear = clearViewModels,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main,
                ) as T
//                FolderListViewModel::class.java -> FolderListViewModel(
//                    repository = foldersRepository,
//                    listLiveDataWrapper = folderListLiveDataWrapper,
//                    folderLiveDataWrapper = folderLiveDataWrapper,
//                    navigation = navigation,
//                    dispatcher = Dispatchers.IO,
//                    dispatcherMain = Dispatchers.Main,
//                ) as T
                else -> throw IllegalArgumentException("no viewmodel found $viewModelClass")
            }
        }
    }
}