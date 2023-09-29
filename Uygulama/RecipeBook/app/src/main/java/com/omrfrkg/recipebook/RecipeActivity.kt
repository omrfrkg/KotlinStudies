package com.omrfrkg.recipebook

import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.omrfrkg.recipebook.databinding.ActivityRecipeBinding
import java.io.ByteArrayOutputStream
import java.io.IOException


class RecipeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecipeBinding
    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher : ActivityResultLauncher<String>
    private var selectedBitmap : Bitmap? = null
    private lateinit var database : SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = this.openOrCreateDatabase("RecipeDb", MODE_PRIVATE,null)

        registerLauncher()

        val intent = intent
        val info = intent.getStringExtra("info")
        if (info.equals("new")){
            binding.recipeNameText.setText("")
            binding.recipeContextText.setText("")
            binding.button.visibility=View.VISIBLE
            binding.imageView.setImageResource(R.drawable.select_image)
            binding.recipeNameText.isEnabled = true
            binding.recipeContextText.isEnabled = true
            binding.imageView.isClickable = true
        }
        else{
            binding.recipeNameText.isEnabled = false
            binding.recipeContextText.isEnabled = false
            binding.imageView.isClickable = false
            binding.button.visibility=View.INVISIBLE
            val selectedId = intent.getIntExtra("id", 0)

            val cursor = database.rawQuery("Select * From recipe WHERE id = ?", arrayOf(selectedId.toString()))

            val recipeNameIx = cursor.getColumnIndex("recipename")
            val recipeContextIx = cursor.getColumnIndex("recipecontext")
            val imageIx = cursor.getColumnIndex("image")

            while (cursor.moveToNext()){
                binding.recipeNameText.setText(cursor.getString(recipeNameIx))
                binding.recipeContextText.setText(cursor.getString(recipeContextIx))

                var byteArray = cursor.getBlob(imageIx)
                val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
                binding.imageView.setImageBitmap(bitmap)
            }

            cursor.close()
        }

    }

    fun save(view : View){


        var recipeNameText = binding.recipeNameText.text.toString()
        var recipeContextText = binding.recipeContextText.text.toString()

        if (selectedBitmap == null){
            val imageNotSelected : Bitmap = BitmapFactory.decodeResource(resources,R.drawable.image_not_selected)
            selectedBitmap = imageNotSelected
        }

        if (recipeContextText.isEmpty() || recipeNameText.isEmpty()){
            Toast.makeText(this@RecipeActivity,"Boş yer bırakılamaz!", Toast.LENGTH_SHORT).show()
        }
        else{
            val smallBitmap = makeSmallerBitmap(selectedBitmap!!,300)

            val outputStream = ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArray = outputStream.toByteArray()

            try{
                database.execSQL("CREATE TABLE IF NOT EXISTS recipe (id INTEGER PRIMARY KEY,recipename VARCHAR,recipecontext VARCHAR,image BLOB)")

                val sqlString = "INSERT INTO recipe (recipename,recipecontext,image) VALUES (?, ?, ?)"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1,recipeNameText)
                statement.bindString(2,recipeContextText)
                statement.bindBlob(3,byteArray)
                statement.execute()
            }
            catch (e : Exception){
                e.printStackTrace()
            }
        }

        val intent = Intent(this@RecipeActivity,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun makeSmallerBitmap(image: Bitmap,maximumSize : Int) : Bitmap{

        var width = image.width
        var height = image.height

        val bitmapRatio : Double = width.toDouble() / height.toDouble()

        if (bitmapRatio > 1){
            //landscape

            width = maximumSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()
        }
        else{
            //portrait
            height = maximumSize
            val scaledWidth = height * bitmapRatio
            width = scaledWidth.toInt()
        }

        return Bitmap.createScaledBitmap(image,width,height,true)
    }

    fun selectImage(view : View){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            //Android 33+ -> READ_MEDIA_IMAGES

            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_MEDIA_IMAGES)){
                    //rationale

                    Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",
                        //request permission

                        View.OnClickListener {
                            permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                        }).show()
                }
                else{
                    //request permission
                    permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                }
            }
            else{
                //intent
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }
        else{
            //Android 32- -> READ_EXTERNAL_STORAGE

            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                    //rationale

                    Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",
                        //request permission

                        View.OnClickListener {
                            permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        }).show()
                }
                else{
                    //request permission

                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
            else{
                //intent
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }

    }

    private fun registerLauncher(){
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intentFromResult = result.data
                if (intentFromResult != null) {
                    val imageData = intentFromResult.data
                    try {
                        if (Build.VERSION.SDK_INT >= 28) {
                            val source = ImageDecoder.createSource(this@RecipeActivity.contentResolver, imageData!!)
                            selectedBitmap = ImageDecoder.decodeBitmap(source)
                            binding.imageView.setImageBitmap(selectedBitmap)
                        } else {
                            selectedBitmap = MediaStore.Images.Media.getBitmap(this@RecipeActivity.contentResolver, imageData)
                            binding.imageView.setImageBitmap(selectedBitmap)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            else{
                //galeriye girildi ama resim seçilmedi

                val imageData = Uri.parse("android.resource://com.omrfrkg.recipebook/drawable/image_not_selected")
                try {
                    if (Build.VERSION.SDK_INT >= 28) {
                        val source = ImageDecoder.createSource(this@RecipeActivity.contentResolver, imageData!!)
                        selectedBitmap = ImageDecoder.decodeBitmap(source)
                        binding.imageView.setImageBitmap(selectedBitmap)
                    } else {
                        selectedBitmap = MediaStore.Images.Media.getBitmap(this@RecipeActivity.contentResolver, imageData)
                        binding.imageView.setImageBitmap(selectedBitmap)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                //permission granted
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            } else {
                //permission denied
                Toast.makeText(this@RecipeActivity, "Permisson needed!", Toast.LENGTH_LONG).show()
            }
        }
    }
}