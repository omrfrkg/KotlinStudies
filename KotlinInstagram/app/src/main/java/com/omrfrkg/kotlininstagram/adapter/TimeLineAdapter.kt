package com.omrfrkg.kotlininstagram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.kotlininstagram.databinding.RecycleRowBinding
import com.omrfrkg.kotlininstagram.model.Post
import com.omrfrkg.kotlininstagram.model.Profil
import com.omrfrkg.kotlininstagram.model.Singleton
import com.squareup.picasso.Picasso

class TimeLineAdapter(private val timeLineList : ArrayList<Post>,private val profilList : ArrayList<Profil>,private val mail : String) : RecyclerView.Adapter<TimeLineAdapter.TimeLineHolder>() {
    class TimeLineHolder(val binding : RecycleRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineHolder {
        val binding = RecycleRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TimeLineHolder(binding)
    }

    override fun getItemCount(): Int {
        return timeLineList.size
    }



    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        val email : String = timeLineList.get(position).email
        val emailIx : Int = email.indexOf('@')
        val username : String = email.substring(0,emailIx)

        holder.binding.recyclerUsername.text = username
        holder.binding.recyclerUsernameSecond.text = username
        holder.binding.recycleRowComment.text = timeLineList.get(position).comment
        Picasso.get().load(timeLineList.get(position).downloadUrl).into(holder.binding.recycleRowImg)

        for (i in 0 until profilList.size){
            /*for (j in 0 until timeLineList.size){
                if(profilList[i].email == timeLineList[j].email){
                    Picasso.get().load(profilList[i].photo).into(holder.binding.profilPhoto)
                }
            }*/

            if(profilList[i].email == email){
                Picasso.get().load(profilList[i].photo).into(holder.binding.profilPhoto)
            }
        }

        /*for (item in timeLineList){
            for (item2 in profilList){
                if (item.email == item2.email){
                    Picasso.get().load(item2.photo).into(holder.binding.profilPhoto)
                }
            }
        }*/
    }
}