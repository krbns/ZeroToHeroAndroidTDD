package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val TITLE_REMOVED = "TITLE_VISIBILITY"
class MainActivity : AppCompatActivity() {
    private var titleTextView: TextView? = null
    private var removeButton: Button? = null
    private var rootLayout: LinearLayout? = null

    private var titleRemoved: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            titleRemoved = savedInstanceState.getBoolean(TITLE_REMOVED)
        }

        titleTextView = findViewById(R.id.titleTextView)
        removeButton = findViewById(R.id.removeButton)
        rootLayout = findViewById(R.id.rootLayout)

        if (titleRemoved) {
            rootLayout?.removeView(titleTextView)
        }

        removeButton?.setOnClickListener {
        titleRemoved = true
            rootLayout?.removeView(titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(TITLE_REMOVED, titleRemoved)
    }

}