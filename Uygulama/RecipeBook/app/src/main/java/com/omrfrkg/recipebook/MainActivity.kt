package com.omrfrkg.recipebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.omrfrkg.recipebook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var recipeList : ArrayList<Recipe>
    private lateinit var recipeAdapter : RecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeList = ArrayList<Recipe>()

        recipeAdapter = RecipeAdapter(recipeList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = recipeAdapter

        try{
            val database = this.openOrCreateDatabase("RecipeDb", MODE_PRIVATE,null)

            val cursor = database.rawQuery("SELECT * FROM recipe",null)

            val recipeNameIx = cursor.getColumnIndex("recipename")
            val idIx = cursor.getColumnIndex("id")
            val imageIx = cursor.getColumnIndex("image")

            while (cursor.moveToNext()){
                val recipeName = cursor.getString(recipeNameIx)
                val id = cursor.getInt(idIx)
                val image = cursor.getBlob(imageIx)
                val recipe = Recipe(id,recipeName,image)
                recipeList.add(recipe)
            }

            recipeAdapter.notifyDataSetChanged()
            cursor.close()

        }
        catch (e : Exception){

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflater
        val  menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addRecipe){
            val intent = Intent(this@MainActivity,RecipeActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


}