package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val TITLE_TEXT = "TITLE_TEXT"
class MainActivity : AppCompatActivity() {
    private var titleTextView: TextView? = null
    private var changeButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTextView = findViewById(R.id.titleTextView)
        changeButton = findViewById(R.id.changeButton)

        if (savedInstanceState != null) {
            titleTextView?.text = savedInstanceState.getString(TITLE_TEXT)
        }

        changeButton?.setOnClickListener {
            titleTextView?.text = "I am an Android Developer!"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TITLE_TEXT, titleTextView?.text.toString())
    }

}