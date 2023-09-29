package com.omrfrkg.intentkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.omrfrkg.intentkotlin.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getIntent = intent
        val name = getIntent.getStringExtra("name")

        if (name != null){
            binding.secondActivityNameText.text = "Name : $name"
        }
        else{
            Toast.makeText(this@SecondActivity,"No Value!",Toast.LENGTH_LONG).show()
        }

    }

    fun goToFirstActivity(view : View){
        val intent = Intent(this@SecondActivity,MainActivity::class.java)
        startActivity(intent)
    }
}