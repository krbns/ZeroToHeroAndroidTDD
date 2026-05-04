package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.data.database.AppDataBase

class MyApp : Application(), ProvideViewModel {

    private lateinit var factory: ProvideViewModel.Factory

    private val clear: ClearViewModel = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            factory.clear(clasz)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val database = AppDataBase.getInstance(this)

//        factory = ProvideViewModel.Factory(ProvideViewModel.Base())
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
        return factory.viewModel(clasz)
    }
}