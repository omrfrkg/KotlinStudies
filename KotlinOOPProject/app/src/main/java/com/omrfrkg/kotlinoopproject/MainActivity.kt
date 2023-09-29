package com.omrfrkg.kotlinoopproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //constructor

        var user = User("ömer",25)
        user.name = "Serdar"
        user.age = 25

        println(user.name+" "+user.age)

        //encapsulation

        var james = Musician("James","Guitar",55)
        println(james.age.toString())
        println(james.returnBandName("Ömer"))
        println(james.returnBandName("Kirk"))

        //inheritance
        var lars = SuperMusician("Lars","Drums",65)
        println(lars.name)
        println(lars.returnBandName("Ömer"))
        lars.sing()

        //polymorphism

        //static polymorphism
        val math = Mathematics()
        println(math.sum())
        println(math.sum(1,2))
        println(math.sum(1,2,3))


        //dynamic polymorphism
        val animal = Animal()
        animal.sing()

        val barley = Dog()
        barley.test()
        barley.sing()

        // abstract & interface

        var myPiano = Piano()
        myPiano.brand = "Yamaha"
        myPiano.digital = false
        println(myPiano.roomName)
        myPiano.info()
        myPiano.sing()

        //Lambda expressions

        /*fun printString(myString: String){
            println(myString)
        }

        printString("My Test String")*/

        val testString = {myString: String -> println(myString) }

        testString("My Lambda String")

        val multiplyLambda = {a : Int,b : Int -> a * b}
        val multiplyLambdaV2 : (Int,Int) -> Int = {a,b -> a*b}
        println(multiplyLambda(5,3))
        println(multiplyLambdaV2(5,3))

        //asyncrnous

        //callback function, listener function, complation function

        fun downdloadMusicans(url : String,complation : (Musician) -> Unit){

            //url -> download
            //data
            val kirkHammet = Musician("Kirk","Guitar",60)
            complation(kirkHammet)
        }

        downdloadMusicans("metallica.com",{ musician ->
            //println("completed && ready")
            println(musician.name)
        })






    }
}