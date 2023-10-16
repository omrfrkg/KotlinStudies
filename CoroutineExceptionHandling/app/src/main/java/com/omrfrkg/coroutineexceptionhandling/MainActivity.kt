package com.omrfrkg.coroutineexceptionhandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception : ${throwable.localizedMessage}")
        }

        lifecycleScope.launch(handler){
            launch {
                throw Exception("error")
            }

        }

        lifecycleScope.launch(handler) {
            supervisorScope {

                launch {
                    throw Exception("error2")
                }

                launch {
                    delay(500L)
                    println("this is executed")
                }
            }

        }

        CoroutineScope(Dispatchers.Main + handler).launch {
            throw Exception("error in a coroutine scope")
        }



    }
}