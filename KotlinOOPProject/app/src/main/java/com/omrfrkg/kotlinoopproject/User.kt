package com.omrfrkg.kotlinoopproject

class User : People{

    //Property
    var name : String? = null
    var age : Int? = null

    //Constructor vs Init

    constructor(nameInput: String,ageInput : Int){
        this.name = name
        this.age = age
        println("user created")
    }
    init {
        println("init")
    }
}