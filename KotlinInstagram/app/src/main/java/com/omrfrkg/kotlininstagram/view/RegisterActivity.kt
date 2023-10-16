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
import com.omrfrkg.kotlininstagram.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.mailText.addTextChangedListener(textWatcher)
        binding.sifreText.addTextChangedListener(textWatcher)

        binding.kayitOlBtn.isEnabled = false


    }

    fun kayitOl(view : View){

        val email = binding.mailText.text.toString()
        val password = binding.sifreText.text.toString()

        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            //success
            val intent = Intent(this@RegisterActivity, ProfilPictureActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@RegisterActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private val textWatcher  = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            val mailText = binding.mailText.text.toString().trim()
            val sifreText = binding.sifreText.text.toString().trim()

            binding.kayitOlBtn.isEnabled = mailText.isNotEmpty() && sifreText.isNotEmpty()

        }

    }
}