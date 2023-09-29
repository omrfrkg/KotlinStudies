package com.omrfrkg.runnablekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.omrfrkg.runnablekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var runnable: Runnable = Runnable { }
    private var handler : Handler = Handler(Looper.getMainLooper())
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun start(view : View){
        number = 0

        runnable = Runnable {
            number += 1
            binding.timeText.text = "Time : $number"

            handler.postDelayed(runnable,1000)
        }

        handler.post(runnable)
        binding.startButton.isEnabled = false
    }

    fun stop(view : View){
        binding.startButton.isEnabled = true
        number = 0
        binding.timeText.text = "Time :0"
        handler.removeCallbacks(runnable)
    }
}