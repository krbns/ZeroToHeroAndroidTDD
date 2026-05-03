package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.core.ViewModelFactory
import ru.easycode.zerotoheroandroidtdd.data.database.ItemsDataBase

class MyApp : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory
    private val clear: ClearViewModel = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            factory.clearViewModel(clasz)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val itemsDataBase = ItemsDataBase.getInstance(this)
        factory = ViewModelFactory.Base(ProvideViewModel.Base(clear, itemsDataBase.itemsDao()))
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
        return factory.viewModel(clasz)
    }
}