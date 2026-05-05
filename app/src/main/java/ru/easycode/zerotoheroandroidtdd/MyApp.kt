package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.core.nav.Navigation
import ru.easycode.zerotoheroandroidtdd.data.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.data.NotesRepository
import ru.easycode.zerotoheroandroidtdd.data.database.AppDataBase
import ru.easycode.zerotoheroandroidtdd.features.edit.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.features.edit.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.features.folders.FolderListLiveDataWrapper

class MyApp : Application(), ProvideViewModel {

    private lateinit var factory: ProvideViewModel.Factory

    private val clear: ClearViewModels = object : ClearViewModels {
        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            viewModelClasses.forEach {
                factory.clear(it)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        val database = AppDataBase.getInstance(this)
        val now = Now.Base()

        val navigation = Navigation.Base()

        val foldersRepository = FoldersRepository.Base(
            now = now,
            foldersDao = database.foldersDao(),
            notesDao = database.notesDao()
        )
        val notesRepository = NotesRepository.Base(
            now = now,
            dao = database.notesDao()
        )

        val base = ProvideViewModel.Base(
            navigation = navigation,
            folderLiveDataWrapper = FolderLiveDataWrapper.Base(),
            folderListLiveDataWrapper = FolderListLiveDataWrapper.Base(),
            noteListLiveDataWrapper = NoteListLiveDataWrapper.Base(),
            noteLiveDataWrapper = NoteLiveDataWrapper.Base(),
            notesRepository = notesRepository,
            foldersRepository = foldersRepository,
            clearViewModels = clear,
        )

        factory = ProvideViewModel.Factory(base)
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
        return factory.viewModel(clasz)
    }
}