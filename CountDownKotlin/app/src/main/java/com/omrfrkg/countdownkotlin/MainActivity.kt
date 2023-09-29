package com.omrfrkg.countdownkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.time)

        object : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                textView.text = "Kalan Süre : ${p0/1000}"
            }

            override fun onFinish() {
                textView.text = "Kalan Süre : 0"
            }
        }.start()

    }
}