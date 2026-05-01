package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var actionButton: Button? = null
    private var inputEditText: TextInputEditText? = null
    private var contentLayout: LinearLayout? = null

    private val items = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionButton = findViewById(R.id.actionButton)
        inputEditText = findViewById(R.id.inputEditText)
        contentLayout = findViewById(R.id.contentLayout)

        if (savedInstanceState != null) {
            val savedItems = savedInstanceState.getStringArrayList("items")

            savedItems?.forEach {
                val textView = TextView(this).apply {
                    this.text = it
                    textSize = 18f
                }

                contentLayout?.addView(textView)
            }
        }

        actionButton?.setOnClickListener {
            val text = inputEditText?.text?.toString().orEmpty()

            if (text.isNotBlank()) {
                items.add(text)
                inputEditText?.setText("")
                val textView = TextView(this).apply {
                    this.text = text
                    textSize = 18f
                }

                contentLayout?.addView(textView)

                inputEditText?.setText("")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("items", ArrayList(items))
    }
}