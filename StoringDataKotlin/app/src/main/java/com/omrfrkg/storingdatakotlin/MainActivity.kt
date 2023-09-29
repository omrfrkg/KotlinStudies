package com.omrfrkg.storingdatakotlin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.omrfrkg.storingdatakotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("com.omrfrkg.storingdatakotlin", MODE_PRIVATE)

        var age = sharedPreferences.getInt("myAge",-1).toString()

        if(age != "-1"){
            binding.textViewAge.text = "Your age : $age"
        }
        else{
            binding.textViewAge.text = "Your age :"
        }



    }

    fun save(view : View){
        val myAge = binding.textAge.text.toString().toIntOrNull()

        if (myAge != null){
            binding.textViewAge.text = "Your age : $myAge"
            sharedPreferences.edit().putInt("myAge",myAge).apply()
        }
        else{
            binding.textViewAge.text = "Enter your age!"
        }

    }

    fun delete(view: View){
        val sharedMemory  = sharedPreferences.getInt("myAge",-1)
        if (sharedMemory != -1){
            sharedPreferences.edit().remove("myAge").apply()
            binding.textViewAge.text = "Your age :"
        }
    }
}