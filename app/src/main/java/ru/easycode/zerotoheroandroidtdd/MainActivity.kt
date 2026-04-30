package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_TITLE_TEXT = "title_text"
    }

    private var actionButton: Button? = null
    private var inputEditText: TextInputEditText? = null
    private var titleTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionButton = findViewById(R.id.actionButton)
        titleTextView = findViewById(R.id.titleTextView)
        inputEditText = findViewById(R.id.inputEditText)

        val text = savedInstanceState?.getString(KEY_TITLE_TEXT)
        if (!text.isNullOrEmpty()) {
            titleTextView?.text = text
        }

        actionButton?.setOnClickListener {
            val text = inputEditText?.text.toString()
            if (text.isNotBlank()) {
                inputEditText?.setText("")
                titleTextView?.text = text
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_TITLE_TEXT, titleTextView?.text.toString())
    }
}