package com.omrfrkg.classesandfunction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var result: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        function()
        sum(4,5)

        result.text = "Result : ${square(6)}"

        button.setOnClickListener {
            result.text = "Button Clicked!"
        }

        val person = Person("Ömer Faruk",25,"Student")

        /*
        person.name = "Ömer Faruk"
        person.age = 25
        person.job = "Student"*/

        person.height = 120

        println(person.name)

        //Nullability

        var myString : String? = null
        var myInt : Int? = null

        // !! -> Eminim ? -> Null Gelebilir

        //1) !!
        //println(myString!! + 5)

        //2)Safe
        //println(myString? + 5)

        //3)
        /*if(myInt != null){
            println(myInt*10)
        }
        else{
            println("myInt is null!")
        }*/

        //4) elvis operator
        //println(myInt?.minus(10) ?: "null")

        //5)
        /*myInt?.let {
            print(it * 5)
        }*/










    }

    fun function(){
        println("Fonksiyon Çalıştırıldı!")
    }

    fun sum(x : Int,y : Int){
        var result : Int = x + y
        println("Result : $result")
    }

    fun square(x : Int) : Int{
        return x * x
    }
}