package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val COUNTER = "COUNTER"
private const val INCREMENT_BUTTON_ENABLE = "INCREMENT_BUTTON_ENABLE"
class MainActivity : AppCompatActivity() {
    private var countTextView: TextView? = null
    private var incrementButton: Button? = null

    private var counter = 0
    private var incrementButtonEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER)
            incrementButtonEnabled = savedInstanceState.getBoolean(INCREMENT_BUTTON_ENABLE)
        }

        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)

        countTextView?.text = counter.toString()
        incrementButton?.isEnabled = incrementButtonEnabled

        incrementButton?.setOnClickListener {
            counter += 2
            if (counter == 4) {
                incrementButtonEnabled = false
                incrementButton?.isEnabled = false
            }
            countTextView?.text = counter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER, counter)
        outState.putBoolean(INCREMENT_BUTTON_ENABLE, incrementButtonEnabled)
    }
}