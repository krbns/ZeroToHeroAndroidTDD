package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val TITLE_VISIBILITY = "TITLE_VISIBILITY"
class MainActivity : AppCompatActivity() {
    private var titleTextView: TextView? = null

    private var titleVisibility = true
    private var changeButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            titleVisibility = savedInstanceState.getBoolean(TITLE_VISIBILITY)
        }

        titleTextView = findViewById(R.id.titleTextView)
        changeButton = findViewById(R.id.hideButton)

        titleTextView?.visibility = if (titleVisibility) View.VISIBLE else View.GONE

        changeButton?.setOnClickListener {
            titleTextView?.visibility = View.GONE
            titleVisibility = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(TITLE_VISIBILITY, titleVisibility)
    }

}