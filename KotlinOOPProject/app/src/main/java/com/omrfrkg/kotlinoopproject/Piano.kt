package com.omrfrkg.kotlinoopproject

class Piano : HouseDecor,Instrument{

    var brand : String? = null
    var digital : Boolean? = null

    override var roomName: String
        get() = "Kitchen"
        set(value) {}

    override fun sing() {
        println("no body")
    }
}