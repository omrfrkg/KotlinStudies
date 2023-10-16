package com.omrfrkg.kotlincoroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    //Coroutine içinde coroutine örneği

    runBlocking {
        launch {
            delay(5000)
            println("run blocking")
        }

        coroutineScope {
            delay(3000)
            println("coroutine scope")
        }
    }
}