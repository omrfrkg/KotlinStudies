package com.omrfrkg.kotlininstagram.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omrfrkg.kotlininstagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.emailText.addTextChangedListener(textWatcher)
        binding.textPassword.addTextChangedListener(textWatcher)

        binding.girisYapBtn.isEnabled = false

        val currentUser = auth.currentUser

        if (currentUser != null){
            val intent = Intent(this@MainActivity, TimeLineActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private val textWatcher  = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            val mailText = binding.emailText.text.toString().trim()
            val sifreText = binding.textPassword.text.toString().trim()

            binding.girisYapBtn.isEnabled = mailText.isNotEmpty() && sifreText.isNotEmpty()

        }

    }

    fun girisYap(view : View){
        val email = binding.emailText.text.toString()
        val password = binding.textPassword.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            val intent = Intent(this@MainActivity, TimeLineActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }


    fun kayitSayfasinaGit(view : View){
        val intent = Intent(this@MainActivity, RegisterActivity::class.java)
        startActivity(intent)
    }
}