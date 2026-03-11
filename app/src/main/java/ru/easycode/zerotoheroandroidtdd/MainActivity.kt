package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val count = Count.Base(step = 2, max = 4, min = 0)
    private var countTextView: TextView? = null
    private var incrementButton: Button? = null
    private var decrementButton: Button? = null
    private var uiState: UiState = count.initial("0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            val restored: UiState? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(KEY, UiState::class.java)
            } else {
                @Suppress("DEPRECATION")
                savedInstanceState.getSerializable(KEY) as? UiState
            }
            if (restored != null) {
                uiState = restored
                count.save(uiState.text)
            }
        }


        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)

        uiState.apply(
            countTextView,
            incrementButton,
            decrementButton
        )

        incrementButton?.setOnClickListener {
            uiState = count.increment(countTextView?.text.toString())
            uiState.apply(
                countTextView,
                incrementButton,
                decrementButton
            )
        }

        decrementButton?.setOnClickListener {
            uiState = count.decrement(countTextView?.text.toString())
            uiState.apply(
                countTextView,
                incrementButton,
                decrementButton
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    companion object {
        private const val KEY = "uiStateKey"
    }
}