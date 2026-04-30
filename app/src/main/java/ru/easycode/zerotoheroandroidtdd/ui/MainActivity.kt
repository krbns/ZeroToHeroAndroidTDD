package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.data.SimpleService
import ru.easycode.zerotoheroandroidtdd.domain.Repository

private const val HELLO_WORLD_URL =
    "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"

class MainActivity : AppCompatActivity() {

    private var actionButton: Button? = null
    private var progressBar: ProgressBar? = null
    private var titleTextView: TextView? = null

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.google.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val viewModel = MainViewModel(
        DefaultLiveDataWrapper(),
        Repository.Base(retrofit.create(SimpleService::class.java), HELLO_WORLD_URL)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            viewModel.restore(DefaultBundleWrapper(it))
        }
        actionButton = findViewById(R.id.actionButton)
        progressBar = findViewById(R.id.progressBar)
        titleTextView = findViewById(R.id.titleTextView)

        actionButton?.setOnClickListener { viewModel.load() }

        viewModel.singleLiveDataWrapper.liveData().observe(this) { state ->
            when (state) {
                is UiState.ShowData -> {
                    titleTextView?.text = state.text
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(DefaultBundleWrapper(outState))
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}