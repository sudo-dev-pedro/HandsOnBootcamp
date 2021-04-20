package com.vitorota.androidthreads

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class HandlerLooperActivity : AppCompatActivity() {

    private var customThread: MyCustomThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_looper)
        title = "Teste"

        customThread = MyCustomThread()
        customThread?.start()
    }

    fun clickActionCustomThread(view: View) {
        customThread?.handler?.post {
            Log.d("HandlerLooper", "main thread posting in customThread")
        }
    }

    fun clickMessageCustomThread(view: View) {
        val message = Message()
        message.data = bundleOf("url" to "https://vitorota.com")
        customThread?.handler?.sendMessage(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        val message = Message()
        message.data = bundleOf("quit" to true)
        customThread?.handler?.sendMessage(message)
    }
}

class MyCustomThread : Thread() {
    var handler: Handler? = null
    override fun run() {
        Looper.prepare()

        handler = Handler(Looper.myLooper()!!){
            processMessage(it)
            true
        }
        Looper.loop()
    }

    private fun processMessage(message:Message){
        Log.d("HandlerLooper", "message received!")
        val url = message.data.getString("url")

        if (url != null) {
            //simulate download of the file
            sleep(3_000)
            Log.d("HandlerLooper", "file $url downloaded!")
        }else{
            val isQuit = message.data.getBoolean("quit", false)
            if(isQuit){
                Looper.myLooper()?.quit()
            }
        }
    }
}