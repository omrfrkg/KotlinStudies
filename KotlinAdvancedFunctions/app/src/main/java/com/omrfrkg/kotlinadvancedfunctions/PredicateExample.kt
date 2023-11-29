package com.omrfrkg.kotlinadvancedfunctions

fun main(){

    val numList = arrayOf<Int>(1,2,3,4,5,6,7,8,9)

    //Hepsi 5'den Büyük Mü?
    val allCheck = numList.all { it > 5 }
    println(allCheck)

    //Herhangi Biri 5'den Büyük Mü?
    val anyCheck = numList.any { it > 5}
    println(anyCheck)

    //5'den Büyük Kaç Sayı Var?
    val totalCount = numList.count { it > 5}
    println(totalCount)

    //5'den Büyük İlk Sayı
    val findNum = numList.find { it > 5 }
    println(findNum)

    //5'den Büyük Olan ve En Sonuncu Olan Eleman
    val findLastNum = numList.findLast { it > 5 }
    println(findLastNum)

    val predicate = {num : Int -> num < 6}
    val allCheckWithPredicate = numList.all(predicate)
    println(allCheckWithPredicate)




}