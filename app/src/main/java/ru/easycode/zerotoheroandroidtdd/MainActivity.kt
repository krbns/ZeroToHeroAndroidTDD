package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter = ItemsAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.actionButton.setOnClickListener {
            val text = binding.inputEditText.text?.toString().orEmpty()
            adapter.add(text)
            binding.inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        adapter.save(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle,
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        adapter.restore(savedInstanceState)
    }
}