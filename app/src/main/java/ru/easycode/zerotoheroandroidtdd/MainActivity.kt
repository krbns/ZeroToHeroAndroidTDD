package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val COUNTER = "COUNTER"
class MainActivity : AppCompatActivity() {
    private var countTextView: TextView? = null
    private var incrementButton: Button? = null

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER)
        }

        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)


        countTextView?.text = counter.toString()

        incrementButton?.setOnClickListener {
            counter += 2
            countTextView?.text = counter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER, counter)
    }

}