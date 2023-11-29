package com.omrfrkg.classesandfunction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var age : EditText
    private lateinit var job : EditText

    private lateinit var result : TextView

    private lateinit var buttonMake : Button

    private var nameSimpson : String? = null
    private var ageSimpson : Int? = null
    private var jobSimpson : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        name = findViewById(R.id.textName)
        age = findViewById(R.id.textAge)
        job = findViewById(R.id.textJob)

        result = findViewById(R.id.textResult)

    }

    fun makeSimpson(view : View){
        nameSimpson = name.text.toString()
        ageSimpson = age.text.toString().toIntOrNull()
        jobSimpson = job.text.toString()

        result.text = "$nameSimpson $ageSimpson $jobSimpson"
    }
}