package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.data.DefaultRepository

class MainActivity : AppCompatActivity() {

    private var actionButton: Button? = null
    private var progressBar: ProgressBar? = null
    private var titleTextView: TextView? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MainViewModelFactory(DefaultLiveDataWrapper(), DefaultRepository())
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        actionButton = findViewById(R.id.actionButton)
        progressBar = findViewById(R.id.progressBar)
        titleTextView = findViewById(R.id.titleTextView)

        actionButton?.setOnClickListener { viewModel.load() }

        viewModel.liveData.liveData().observe(this) { state ->
            when (state) {
                is UiState.ShowData -> {
                    titleTextView?.visibility = View.VISIBLE
                    actionButton?.isEnabled = true
                    progressBar?.visibility = View.GONE
                }

                is UiState.ShowProgress -> {
                    actionButton?.isEnabled = false
                    progressBar?.visibility = View.VISIBLE
                }
            }
        }
    }
}