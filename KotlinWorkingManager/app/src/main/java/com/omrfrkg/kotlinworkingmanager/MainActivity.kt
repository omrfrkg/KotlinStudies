package com.omrfrkg.kotlinworkingmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val data = Data.Builder().putInt("intKey",1).build()

        val constraints = Constraints.Builder()
                                     //.setRequiredNetworkType(NetworkType.CONNECTED)
                                     .setRequiresCharging(false)
                                     .build()

        //Bir defa çalıştırmak için

        /*
        val myWorkRequest : WorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            //.setInitialDelay(5,TimeUnit.HOURS)
            //.addTag("myTag")
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)
         */

        //Periyodik İşlemler İçin(Örnekteki 15 dakikada 1)
        //Yapılabilicek işlemin en az 15 dakika olması gerekiyor.
        val myWorkRequest : WorkRequest = PeriodicWorkRequestBuilder<RefreshDatabase>(15,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id).observe(this,
            Observer {
                if (it.state == WorkInfo.State.RUNNING){
                    println("running")
                }
                else if(it.state == WorkInfo.State.FAILED){
                    println("failed")
                }
                else if (it.state == WorkInfo.State.SUCCEEDED){
                    println("succeeded")
                }
            })

        //İş İptal Etmek İçin

        //Bütün işleri iptal etmek için
        //WorkManager.getInstance(this).cancelAllWork()

        //Chaining - Zincirleme

        /*
        val oneTimeRequest : OneTimeWorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this)
            .beginWith(oneTimeRequest)
            .then(oneTimeRequest)
            .then(oneTimeRequest)
            .enqueue()

         */


    }
}