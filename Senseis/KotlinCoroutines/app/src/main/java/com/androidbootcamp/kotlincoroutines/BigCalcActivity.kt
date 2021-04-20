package com.androidbootcamp.kotlincoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androidbootcamp.kotlincoroutines.databinding.ActivityBigCalcBinding

class BigCalcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBigCalcBinding
    private val viewModel by lazy { ViewModelProvider(this).get(BigCalcViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBigCalcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartCalc.setOnClickListener {
            viewModel.startBigCalc()
        }

        viewModel.result.observe(this) {
            binding.textResult.text = it
        }
    }
}