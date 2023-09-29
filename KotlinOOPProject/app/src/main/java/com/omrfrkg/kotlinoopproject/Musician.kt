package com.omrfrkg.kotlinoopproject

open class Musician(name : String, instrument : String, age : Int) {
    var name : String? = name
        private set
        get

    private var instrument : String? = instrument
        private set
        get

    var age : Int? = age
        get
        private set

    private val bandName : String = "Metallica"

    fun returnBandName(password : String) : String{
        if (password == "Ömer"){
            return bandName
        }
        else{
            return "Wrong Password!"
        }
    }

}