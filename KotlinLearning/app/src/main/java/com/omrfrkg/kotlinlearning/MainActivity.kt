package com.omrfrkg.kotlinlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(4*5)
        println("Merhaba")

        //Variables && Constants

        println("------ Variables && Constants ------")

        var x = 5
        var y = 4

        println(x*y)

        var age = 35
        println(age/x)

        val name = "James"
        val a = 5

        //Integer

        println("------ Integer ------")

        var numberOne = 5
        var numberTwo = 9
        val result = 9*5
        println(result)

        //Defining
        val numberThree : Int

        //Initialize
        numberThree = 3

        //Long
        var xx : Long = 500

        //Double & Float

        println("------ Double & Float ------")
        val pi  = 3.14
        println(pi*2.0)

        val myFloat = 3.14f
        println(myFloat*2)

        var myAge : Double = 23.0
        println(myAge/2)

        //String
        println("------ String ------")

        val myString = "Ömer Faruk Gündoğdu"
        var myName = "Ömer"
        var mySurname = "Gündoğdu"

        //val fullName = myName + " " + mySurname
        val fullName = "$myName $mySurname"
        println(fullName)

        //Boolean
        println("----- Boolean -----")
        var myBoolean : Boolean = true
        myBoolean = false

        var isAslive = true

        // < , > , <= , >= , == , != , && , ||

        println(3 < 5)
        println(3 > 5)
        println(3 == 3)
        println(4 != 5)

        //Conversion
        println("----- Conversion -----")

        var num = 5
        var myLongNumber = num.toLong()
        println(myLongNumber)

        var input = "10"
        var inputInteger = input.toInt()
        println(inputInteger * 2)

        //Collections

        //Arrays

        println("----- Array -----")

        val myArray = arrayOf("James","Kirk","Rob","Lars")

        //index
        println(myArray[0])
        myArray[0] = "James Hetfield"
        println(myArray[0])

        myArray.set(1,"Kirk Hammett")
        println(myArray[1])

        val numberArray = arrayOf(1,2,3,4,5)
        println(numberArray[3])

        val myNewArray = doubleArrayOf(1.0,2.0,3.0)

        val mixedArray = arrayOf("Ömer",5)
        println(mixedArray[0])
        println(mixedArray[1])

        //List - ArrayList

        println("----- List -----")

        val myMusician = arrayListOf<String>("James","Kirk")

        myMusician.add("Lars")
        println(myMusician[2])

        myMusician.add(0,"Rob")
        println(myMusician[0])

        val myArrayList = ArrayList<Int>()
        myArrayList.add(1)
        myArrayList.add(200)

        val myMixedArrayList = ArrayList<Any>()
        myMixedArrayList.add("Ömer")
        myMixedArrayList.add(12.25)
        myMixedArrayList.add(true)

        println(myMixedArrayList[0])
        println(myMixedArrayList[1])
        println(myMixedArrayList[2])

        //Set

        println("----- Set -----")

        val myExampleArray = arrayOf(1,1,2,3,4)

        println("element 1: ${myExampleArray[0]}")
        println("element 2: ${myExampleArray[1]}")

        val mySet = setOf<Int>(1,1,2,3)

        println(mySet.size)

        //For Each

        mySet.forEach{println(it)}

        val myStringSet = HashSet<String>()
        myStringSet.add("Ömer")
        myStringSet.add("Ömer")
        println(myStringSet.size)

        //Map - HashMap

        println("----- Map -----")

        //Key - Value

        val fruitArray = arrayOf("Apple","Banana")
        val caloriesArray = arrayOf(100,200)

        println("${fruitArray[0]} : ${caloriesArray[0]}")

        val fruitCalorieMap = hashMapOf<String,Int>()
        fruitCalorieMap.put("Apple",100)
        fruitCalorieMap.put("Banana",150)
        println(fruitCalorieMap["Apple"])

        val myHashMap = HashMap<String,String>()

        val myNewMap = hashMapOf<String,Int>("A" to 1,"B" to 2,"C" to 3)
        println(myNewMap["C"])

        //Operator

        println("----- Operator -----")

        var m = 5
        println(m)
        m = m + 1

        println(m)
        m++

        println(m)
        m--

        println(m)

        var n = 7

        println(n > m)

        // &&
        // ||

        println(n > m && 1 > 2)
        println(n > m || 1 > 2)

        println(10+2)
        println(10-2)
        println(10*2)
        println(10/2)

        //Remainder
        println(10%4)

        //If Control

        println("----- If Control -----")

        val myNumberAge = 52

        if (myNumberAge < 30){
            println("< 30")
        }
        else if (myNumberAge >= 30 && myNumberAge < 40){
            println("30 - 40")
        }
        else if(myNumberAge >= 40 && myNumberAge < 50){
            println("40 - 50")
        }
        else{
            println(">= 50")
        }

        //Switch - When

        println("----- Switch - When -----")

        val day = 3
        var dayString = ""

        when(day){
            1 -> dayString = "Monday"
            2 -> dayString = "Tuesday"
            3 -> dayString = "Wednesday"
            else -> dayString = ""
        }

        print(dayString)


        //Loops

        //For Loop

        println("----- For Loop -----")

        val myArrayOfNumbers = arrayOf(12,15,18,21,24,27,30,33)
        val q = myArrayOfNumbers[0] / 3 * 5
        println(q)

        for (num in myArrayOfNumbers){
            val z = num / 3 * 5
            println(z)
        }

        for (i in myArrayOfNumbers.indices){
            val qz = myArrayOfNumbers[i] / 3 * 5
            println(qz)
        }

        for(b in 0..9){
            println(b)
        }

        val myStringArrayList = ArrayList<String>()
        myStringArrayList.add("Ömer")
        myStringArrayList.add("Gündoğdu")
        myStringArrayList.add("Faruk")

        for (str in myStringArrayList){
            println(str)
        }

        myStringArrayList.forEach{println(it)}

        //While Loop

        println("----- While Loop -----")

        var j = 0

        while (j < 10){
            println(j)
            j = j + 1
        }






    }
}