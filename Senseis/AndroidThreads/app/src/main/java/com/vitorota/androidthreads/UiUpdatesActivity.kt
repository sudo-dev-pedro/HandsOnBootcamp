package com.vitorota.androidthreads

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UiUpdatesActivity : AppCompatActivity() {

    private val textView: TextView by lazy {
        findViewById(R.id.textView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uiupdates)
        title = "UI Updates"
    }

    fun clickBackground(view: View) {
        val thread = Thread {
            //simulate a data fetch
            Thread.sleep(2_000)
            textView.text = "Data fetched! background"
        }

        thread.start()
    }

    fun clickRunOnUIThread(view: View) {
        val thread = Thread {
            //simulate a data fetch
            Thread.sleep(2_000)
            runOnUiThread {
                textView.text = "Data fetched! ui thread"
            }
        }

        thread.start()
    }
}