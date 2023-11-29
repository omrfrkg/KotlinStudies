package com.omrfrkg.kotlinadvancedfunctions

fun main(){
    val myNumList = listOf<Int> (1,3,5,7,9,11,13,15)

    val newNumList = myNumList.filter {  it < 6 }

    for(item in newNumList){
        println(item)
    }

    val mapNumList = myNumList.map { it*it }

    for (item in mapNumList){
        println(item)
    }

    val mapAndFilterMixList = myNumList.filter { it < 6 }.map { it*it }

    for (eleman in mapAndFilterMixList){
        println(eleman)
    }

    val musician = listOf<Musician>(
        Musician("Ömer",26,"Gitar"),
        Musician("Serdar",22,"Klarnet"),
        Musician("Sencer",30,"Darbuka")
    )

    val whichInstrument = musician.filter { it.age < 30 }.map { it.name+" "+it.instrument+" çalıyor" }

    for (i in whichInstrument){
        println(i)
    }



}

data class Musician(val name : String,val age : Int,val instrument : String){

}