package com.omrfrkg.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Light Weightness
        //Thread'lere göre daha işlevsel ve sıkıntısız
        /*GlobalScope.launch {
            repeat(100_000){
                launch {
                    println("android")
                }
            }
        }*/

        //Scope

        //Global Scope
        //Bütün Uygulama içerisinden erişilebilicek kapsamda açıyor coroutine
        //Genelde kullanılmaz.
        /*("global scope start...")
        GlobalScope.launch{
            delay(5000)
            println("global scope...")
        }
        println("global scope end...")*/

        //runBlocking
        //Bloklayarak bir scope oluşturuyor.Ne demek blokluyor yani işlem sırası ona geldiği zaman içerisindeki işlem bitene kadar uygulamayı blokluyor.
        //Sadece tek bir coroutine çalıştırıcaksa yazılabilir.Çok fazla kullanılmaz.

        /*println("run blocking start...")
        runBlocking {
            launch {
                delay(5000)
                println("run blocking...")
            }
        }
        println("run blocking end...")*/

        //CoroutineScope
        //İçerisindeki bütün coroutineler bitene kadar çalışmaya devam ediyor.
        /*println("coroutine scope start...")
        CoroutineScope(Dispatchers.Default).launch{
            delay(5000)
            println("coroutine scope...")
        }
        println("coroutine scope end...")*/


        //Dispatchers

        //Dispatchers.Default -> CPU Intensive (İşlemci Çok Kullanılır)
        //Görsel işleme,Çok uzun bir diziyi alfabetik olarak dizme

        //Dispatchers.IO -> Input - Output
        //Networking işlemleri (İnternetten veri çekme, Bir veritabanından veri alma)

        //Dispatchers.Main -> UI
        //Kullanıcı arayüzü ile yapılan ilgili işlemler

        //Dispatchers.Unconfined -> Inherited dispatchers
        //İçersinideki yazılan yere göre değişiyor(Ayarlamaları kendiside yapıyor denebilir)

        /*runBlocking {

            launch(Dispatchers.Main) {
                println("Main Thread : ${Thread.currentThread().name}")
            }

            launch(Dispatchers.Default) {
                println("Default Thread : ${Thread.currentThread().name}")
            }

            launch(Dispatchers.IO) {
                println("IO Thread : ${Thread.currentThread().name}")
            }

            launch(Dispatchers.Unconfined) {
                println("Unconfined Thread : ${Thread.currentThread().name}")
            }

         */


        //Oluşturduğumuz özel fonksiyonların içinde coroutine kullanmak istiyorsak suspend etmeliyiz.
        //Suspend edilen özel fonksiyonlar main içerisinde coroutine scope dışında çağırılamaz.

        /*runBlocking {
            delay(2000)
            println("run blocking")
            mySpecialFunction()
        }
         */

        //Async Coroutine

        /*var username : String = ""
        var age : Int = 0

        runBlocking {

            val downloadedAge = async {
                downloadAge()
            }

            val downloadedUsername = async {
                downloadUsername()
            }


            username = downloadedUsername.await()
            age  = downloadedAge.await()
        }

        println("${username} ${age}")
         */

        //Job Coroutine

        /*runBlocking {

            val job = launch {
                delay(2000)
                println("Job Start")

                val secondJob = launch {
                    println("Second Job")
                }
            }

            job.invokeOnCompletion {
                println("Job Endend")
            }

            job.cancel()

        }*/

        //withContext Coroutine
        //Aynı launch içinden 2 farklı thread için işlem yapmak istersek kullanılır.

        runBlocking {
            launch(Dispatchers.Default) {
                println("Context : $coroutineContext")
                withContext(Dispatchers.IO){
                    println("Context : $coroutineContext")
                }
            }
        }


    }


    /*private suspend fun mySpecialFunction(){
        coroutineScope {
            delay(4000)
            println("Suspend Function")
        }
    }
     */

    /*private suspend fun downloadUsername(): String {
        delay(4000)
        println("download username")
        val username = "Ömer :"
        return username
    }

    private suspend fun downloadAge() : Int{
        delay(4000)
        println("download age")
        val age = 33
        return age
    }
    */
}