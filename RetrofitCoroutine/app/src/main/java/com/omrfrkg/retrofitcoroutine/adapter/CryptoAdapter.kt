package com.omrfrkg.retrofitcoroutine.adapter

import android.graphics.Color
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.retrofitcoroutine.R
import com.omrfrkg.retrofitcoroutine.model.CryptoModel

class CryptoAdapter(private val cryptoModelList : ArrayList<CryptoModel>,private val listener : Listener) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    private val colors : ArrayList<String> = arrayListOf("#80E32D","#2DE366","#34E32D","#CCE32D","#32E6A8","#2DB9E3","#2D47E3","#2D80E3","#2DE3D2","#2DE3D2","#4C2AE5")

    class CryptoHolder(view : View) : RecyclerView.ViewHolder(view){
        private val textName : TextView = view.findViewById(R.id.text_name)
        private val textPrice : TextView = view.findViewById(R.id.text_price)

        fun bind(cryptoModel: CryptoModel,colors : ArrayList<String>,position: Int,listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }

            itemView.setBackgroundColor(Color.parseColor(colors[position%10]))
            textName.text = cryptoModel.currency
            textPrice.text = cryptoModel.price

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reycler_row,parent,false)
        return CryptoHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoModelList.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(cryptoModelList[position],colors,position,listener)
    }
}