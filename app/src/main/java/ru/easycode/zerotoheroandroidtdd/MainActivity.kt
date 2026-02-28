package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var titleTextView: TextView? = null
    private var changeButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleTextView = findViewById(R.id.titleTextView)
        changeButton = findViewById(R.id.changeButton)


        changeButton?.setOnClickListener {
            titleTextView?.text = "I am an Android Developer!"
        }
    }
}