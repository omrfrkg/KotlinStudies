package com.omrfrkg.cevirirehberi

import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.cevirirehberi.databinding.RecyclerRowBinding



class TranslateAdapter(val translateList : ArrayList<Translate>) : RecyclerView.Adapter<TranslateAdapter.TranslateHolder>() {





    class TranslateHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslateHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TranslateHolder(binding)
    }

    override fun getItemCount(): Int {
        return translateList.size
    }

    override fun onBindViewHolder(holder: TranslateHolder, position: Int) {

        holder.binding.recyclerViewId.text = translateList.get(position).word
        holder.itemView.setOnClickListener{
            val intent  = Intent(holder.itemView.context,DetailsActivity::class.java)
            Singleton.selectedTranslate = translateList.get(position)
            holder.itemView.context.startActivity(intent)

        }
    }

}