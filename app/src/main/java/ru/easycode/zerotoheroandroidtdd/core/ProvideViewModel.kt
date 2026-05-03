package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.data.ItemsDao
import ru.easycode.zerotoheroandroidtdd.data.Now
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.details.DetailsViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base(private val clearViewModel: ClearViewModel, private val itemsDao: ItemsDao) :
        ProvideViewModel {
        private val sharedLiveData = ListLiveDataWrapper.Base()

        private val repository = Repository.Base(
            itemsDao,
            Now.Base()
        )

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(
                    repository = repository,
                    liveDataWrapper = sharedLiveData,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main.immediate
                )

                AddViewModel::class.java -> AddViewModel(
                    repository = repository,
                    liveDataWrapper = sharedLiveData,
                    clear = clearViewModel,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main.immediate
                )

                DetailsViewModel::class.java -> DetailsViewModel(
                    deleteLiveDataWrapper = sharedLiveData,
                    repository = repository,
                    clear = clearViewModel,
                    dispatcher = Dispatchers.IO,
                    dispatcherMain = Dispatchers.Main.immediate
                )

                else -> throw IllegalArgumentException("unknown viewModel $viewModelClass")
            } as T
        }
    }
}