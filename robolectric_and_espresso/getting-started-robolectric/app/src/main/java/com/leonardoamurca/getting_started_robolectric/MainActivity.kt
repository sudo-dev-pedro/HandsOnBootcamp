package com.leonardoamurca.getting_started_robolectric

import android.app.Activity
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity() {

    lateinit var textView: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.helloWorldTextView)
        button = findViewById(R.id.simpleButton)
        button.setOnClickListener {
            if (SDK_INT < Build.VERSION_CODES.P) {
                textView.text = "Less than pie"
            } else {
                textView.text = "Ola mundo"
            }
        }
    }
}