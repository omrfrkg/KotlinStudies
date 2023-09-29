package com.omrfrkg.cevirirehberi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.omrfrkg.cevirirehberi.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStartBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = this.getSharedPreferences("com.omrfrkg.cevirirehberi", MODE_PRIVATE)
    }

    fun england(view : View){
       // sharedPreferences.edit().putString("language","English").apply()
       // goToMainMenu()
        val intent = Intent(this@StartActivity,MainActivity::class.java)
        intent.putExtra("language","English")
        startActivity(intent)
    }
    fun germany(view : View){
        //sharedPreferences.edit().putString("language","Deutsch").apply()
        //goToMainMenu()
        val intent = Intent(this@StartActivity,MainActivity::class.java)
        intent.putExtra("language","Deutsch")
        startActivity(intent)
    }
    fun spain(view : View){
        //sharedPreferences.edit().putString("language","Spanisch").apply()
        //goToMainMenu()
        val intent = Intent(this@StartActivity,MainActivity::class.java)
        intent.putExtra("language","Spanisch")
        startActivity(intent)
    }
    fun italy(view : View){
        //sharedPreferences.edit().putString("language","Italiano").apply()
        //goToMainMenu()
        val intent = Intent(this@StartActivity,MainActivity::class.java)
        intent.putExtra("language","Italiano")
        startActivity(intent)
    }

    fun goToMainMenu(){

    }
}