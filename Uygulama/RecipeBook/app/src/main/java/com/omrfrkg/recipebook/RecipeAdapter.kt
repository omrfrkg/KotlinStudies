package com.omrfrkg.recipebook

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.recipebook.databinding.ActivityRecipeBinding
import com.omrfrkg.recipebook.databinding.RecyclerRowBinding

class RecipeAdapter (val recipeList : ArrayList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {
    class RecipeHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecipeHolder(binding)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.binding.recyclerText.text = recipeList.get(position).recipeName

        val image = BitmapFactory.decodeByteArray(recipeList.get(position).image,0,recipeList.get(position).image.size)

        holder.binding.recyclerView.setImageBitmap(image)


        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,RecipeActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("id", recipeList[position].id)
            holder.itemView.context.startActivity(intent)

        }

    }
}