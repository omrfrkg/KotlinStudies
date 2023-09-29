package com.omrfrkg.alertdialogkotlin

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1) Toast 2) AlertDialog 3) Snackbar

        //Context
        //Activity Context -> this
        //Application Context -> applicationContext



        Toast.makeText(this@MainActivity,"Welcome!",Toast.LENGTH_LONG).show()

    }

    fun alert(view : View){
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Alert Title")
        alert.setMessage("Alert Context")

        alert.setPositiveButton("Yes")
        { p0, p1 -> Toast.makeText(this@MainActivity, "Say Yes!", Toast.LENGTH_LONG).show() }

        alert.setNegativeButton("No")
        { p0, p1 -> Toast.makeText(this@MainActivity, "Say No!", Toast.LENGTH_LONG).show() }

        alert.show()
    }



}