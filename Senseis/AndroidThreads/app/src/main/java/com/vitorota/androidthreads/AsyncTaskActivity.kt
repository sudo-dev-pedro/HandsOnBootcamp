package com.vitorota.androidthreads

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AsyncTaskActivity : AppCompatActivity() {
    private val textView: TextView by lazy {
        findViewById(R.id.textView)
    }

    private val progressBar: ProgressBar by lazy {
        findViewById(R.id.progressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asynctask)
        title = "AsyncTask"
    }

    fun buttonClick(view: View) {
        val asyncTask = MyAsyncTask()
        asyncTask.execute()
    }

    inner class MyAsyncTask : AsyncTask<Void, Int, String>() {
        override fun onPreExecute() {
            textView.text = "Downloading..."
        }

        override fun doInBackground(vararg params: Void?): String {
            for(p in 10..100 step 10){
                Thread.sleep(100)
                publishProgress(p)
            }

            return "Downloaded!"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.progress = values.firstOrNull() ?: 0
        }

        override fun onPostExecute(result: String?) {
            textView.text = result
        }
    }
}