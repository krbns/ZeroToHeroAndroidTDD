package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.add.AddFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.delete.DeleteFragment
import ru.easycode.zerotoheroandroidtdd.ui.adapter.ItemsAdapter

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private val adapter = ItemsAdapter(
        onItemClick = { itemUi ->
            supportFragmentManager.beginTransaction()
                .add(R.id.rootLayout, DeleteFragment.newInstance(itemUi.id))
                .addToBackStack(null)
                .commit()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = viewModel(MainViewModel::class.java)

        viewModel.init()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.liveData().observe(this) { list ->
            adapter.update(list)
        }

        binding.addButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.rootLayout, AddFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as ProvideViewModel).viewModel(viewModelClass)
    }
}