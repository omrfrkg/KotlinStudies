package com.omrfrkg.retrofitcoroutine.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.omrfrkg.retrofitcoroutine.R
import com.omrfrkg.retrofitcoroutine.adapter.CryptoAdapter
import com.omrfrkg.retrofitcoroutine.model.CryptoModel
import com.omrfrkg.retrofitcoroutine.service.CryptoAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.util.ExceptionHelper
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),CryptoAdapter.Listener {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<CryptoModel>? = null

    private var adapter : CryptoAdapter? = null
    private lateinit var recyclerView : RecyclerView

    private var job : Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Toast.makeText(this@MainActivity, "Error : ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val layout : LayoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layout

        loadData()


    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
                               .baseUrl(BASE_URL)
                               .addConverterFactory(GsonConverterFactory.create())
                               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                               .build().create(CryptoAPI::class.java)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofit.getData()

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let { it ->
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            adapter = CryptoAdapter(it,this@MainActivity)
                            recyclerView.adapter = adapter
                        }
                    }
                }
            }
        }

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this@MainActivity, "Clicked ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}