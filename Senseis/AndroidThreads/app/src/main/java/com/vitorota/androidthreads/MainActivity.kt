package com.vitorota.androidthreads

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickUiUpdates(view: View) {
        startActivity(Intent(this, UiUpdatesActivity::class.java))
    }

    fun clickSemaphore(view: View) {
        startActivity(Intent(this, SemaphoreActivity::class.java))
    }

    fun clickHandlerLooper(view: View) {
        startActivity(Intent(this, HandlerLooperActivity::class.java))
    }

    fun clickAsyncTask(view: View) {
        startActivity(Intent(this, AsyncTaskActivity::class.java))
    }
}