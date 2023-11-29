package com.omrfrkg.kotlinadvancedfunctions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private var myInt : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newInt = myInt?.let { it + 1 } ?: 0 //Elwis Operatörü ?:

        val ayse = Person("ayşe",22)
        val atil = Person("atil",26)
        val ali = Person("ali",1)

        val insanlar = arrayListOf<Person>(ayse,atil,ali)

        insanlar.filter { it.age > 18 }.also {
            for (i in it){
                println(i.name)
            }
        }

        //apply - with

        Person("arley",2).apply {
            name = "Barley"
        }.also {
            print(it.name)
        }

        val newBarley = Person("bar",56).apply {
            name = "Barley"
        }

        println(newBarley)

        //with

        var anotherBarley = Person("ar",22)

        with(anotherBarley){
            name = "Barley"
            age = 21
        }
        println(anotherBarley.name)

    }

    data class Person(var name : String,var age : Int)
}