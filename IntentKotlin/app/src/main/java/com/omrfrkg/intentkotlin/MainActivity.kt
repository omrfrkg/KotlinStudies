package com.omrfrkg.intentkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.omrfrkg.intentkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun sendName(view : View){
        val name = binding.firstActivityNameText.text.toString()
        if (name != null){
            val intent = Intent(this@MainActivity,SecondActivity::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        }
        else{
            Toast.makeText(this@MainActivity,"Enter Name Please!",Toast.LENGTH_LONG).show()
        }

    }
}