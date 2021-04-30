package com.phro.cardscanpoc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.microblink.blinkcard.entities.recognizers.Recognizer
import com.microblink.blinkcard.entities.recognizers.RecognizerBundle
import com.microblink.blinkcard.entities.recognizers.blinkcard.BlinkCardRecognizer
import com.microblink.blinkcard.uisettings.ActivityRunner
import com.microblink.blinkcard.uisettings.BlinkCardUISettings
import com.phro.cardscanpoc.App.Companion.REQUEST_CODE

class MainActivity : AppCompatActivity() {
    private lateinit var recognizer: BlinkCardRecognizer
    private lateinit var recognizerBundle: RecognizerBundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recognizer = BlinkCardRecognizer()
        recognizerBundle = RecognizerBundle(recognizer)

//        startScanning()
    }

    fun startScanning(view: View) {
        val settings = BlinkCardUISettings(recognizerBundle)

        ActivityRunner.startActivityForResult(this, REQUEST_CODE, settings)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                recognizerBundle.loadFromIntent(data)

                val result: BlinkCardRecognizer.Result = recognizer.result

                if (result.resultState == Recognizer.Result.State.Valid) {
                    result.cardNumber
                    Toast.makeText(this, result.cardNumber, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}