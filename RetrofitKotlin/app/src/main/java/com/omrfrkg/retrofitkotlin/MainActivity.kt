package com.omrfrkg.retrofitkotlin

import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.retrofitkotlin.adapter.CryptoAdapter
import com.omrfrkg.retrofitkotlin.model.CryptoModel
import com.omrfrkg.retrofitkotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ScheduledExecutorService

class MainActivity : AppCompatActivity(), CryptoAdapter.Listener {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<CryptoModel>? = null

    private var adapter : CryptoAdapter? = null
    private lateinit var recyclerView : RecyclerView

    private var compositeDisposable : CompositeDisposable? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)

        //RecyclerView
        val layout : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layout

        compositeDisposable = CompositeDisposable()

        loadData()



    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(retrofit.getData().
                                subscribeOn(Schedulers.io()).
                                observeOn(AndroidSchedulers.mainThread()).
                                subscribe(this::handleResponse))




        /*
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()
        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        cryptoModels.let {
                            adapter = CryptoAdapter(cryptoModels!!,this@MainActivity)
                            recyclerView.adapter = adapter
                        }

                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
         */

    }

    private fun handleResponse(cryptoModel: List<CryptoModel>){

        cryptoModels = ArrayList(cryptoModel)

        cryptoModels.let {
            adapter = CryptoAdapter(cryptoModels!!,this@MainActivity)
            recyclerView.adapter = adapter
        }


    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this@MainActivity, "Clicked ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable?.clear()
    }


}