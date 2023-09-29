package com.omrfrkg.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{

            //Create Database
            val myDatabase  = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)

            //Create Table
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR,age INT)")

            //Add Data
            //myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Kirk',30)")

            //Update Data
            //myDatabase.execSQL("UPDATE musicians SET age = 61 Where name = 'Lars'")
            //myDatabase.execSQL("UPDATE musicians SET name = 'Carls' Where id = 3")

            //Delete Data
            myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Lars'")


            //Get Data
            //val cursor =  myDatabase.rawQuery("SELECT * FROM musicians",null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE id = 3",null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null)
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'",null)
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")




            while (cursor.moveToNext()){
                println("Name :"+cursor.getString(nameIx))
                println("Age :"+cursor.getInt(ageIx))
                println("Id : "+cursor.getInt(idIx))
            }

            cursor.close()


        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }
}