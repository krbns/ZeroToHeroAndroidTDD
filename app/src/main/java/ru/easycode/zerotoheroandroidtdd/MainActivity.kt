package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.ui.DefaultBundleWrapper

class MainActivity : AppCompatActivity() {

    private val adapter = ItemsAdapter()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = (application as MyApp).mainViewModel

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        mainViewModel.liveData().observe(this) {
            adapter.addAll(it)
        }

        binding.actionButton.setOnClickListener {
            val text = binding.inputEditText.text?.toString().orEmpty()
            mainViewModel.add(text)
            binding.inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mainViewModel.save(DefaultBundleWrapper(outState))
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle,
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        mainViewModel.restore(DefaultBundleWrapper(savedInstanceState))
    }
}