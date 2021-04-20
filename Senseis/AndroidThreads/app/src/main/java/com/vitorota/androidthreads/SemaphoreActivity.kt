package com.vitorota.androidthreads

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.vitorota.androidthreads.databinding.ActivitySemaphoreBinding

class SemaphoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySemaphoreBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySemaphoreBinding.inflate(layoutInflater)
        view = binding.root

        setContentView(view)

        title = "Semaphore"

        callLights()
    }

    private fun callLights() {
        val lightsThread = Thread {
            while (true) {
                runOnUiThread {
                    binding.ivCircleGo.setImageResource(R.drawable.green_circle)
                    showTextView(binding.txtGo)
                }

                Thread.sleep(3500)

                runOnUiThread {
                    hideTextView(binding.txtGo)
                    resetViewToGrey(binding.ivCircleGo)
                    binding.ivCircleYellow.setImageResource(R.drawable.yellow_circle)
                    showTextView(binding.txtAtencao)
                }

                Thread.sleep(1500)

                runOnUiThread {
                    hideTextView(binding.txtAtencao)
                    resetViewToGrey(binding.ivCircleYellow)
                    binding.ivCircleRed.setImageResource(R.drawable.red_circle)
                    showTextView(binding.txtPare)
                }

                Thread.sleep(3000)

                runOnUiThread {
                    hideTextView(binding.txtPare)
                    resetViewToGrey(binding.ivCircleRed)
                }
            }
        }
        lightsThread.start()
    }

    private fun resetViewToGrey(view: AppCompatImageView) {
        view.setImageResource(R.drawable.grey_circle)
    }

    private fun showTextView(view: View) {
        view.visibility = View.VISIBLE
    }

    private fun hideTextView(view: View) {
        view.visibility = View.INVISIBLE
    }
}
