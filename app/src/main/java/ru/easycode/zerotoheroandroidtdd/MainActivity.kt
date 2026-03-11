package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var actionButton: Button? = null
    private var progressBar: ProgressBar? = null
    private var titleTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        actionButton = findViewById(R.id.actionButton)
        progressBar = findViewById(R.id.progressBar)
        titleTextView = findViewById(R.id.titleTextView)

        actionButton?.setOnClickListener {
            actionButton?.isEnabled = false
            progressBar?.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                progressBar?.visibility = View.GONE
                titleTextView?.visibility = View.VISIBLE
                actionButton?.isEnabled = true
            }, 3500)
        }
    }
}