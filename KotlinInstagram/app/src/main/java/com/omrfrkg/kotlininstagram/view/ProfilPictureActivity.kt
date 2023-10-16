package com.omrfrkg.kotlininstagram.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.omrfrkg.kotlininstagram.databinding.ActivityProfilPictureBinding
import java.util.UUID

class ProfilPictureActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfilPictureBinding

    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedImg : Uri? = null

    private lateinit var auth : FirebaseAuth
    private lateinit var storage : FirebaseStorage
    private lateinit var firestore : FirebaseFirestore
    private lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilPictureBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerLauncher()

        auth = Firebase.auth
        storage = Firebase.storage
        firestore = Firebase.firestore

    }

    fun uploadProfilPhoto(view : View){

        val uuid = UUID.randomUUID()

        val imageName  = "$uuid.jpg"

        val reference = storage.reference
        val imageReference = reference.child("user_profil_photo").child(imageName)

        if (selectedImg != null){
            imageReference.putFile(selectedImg!!).addOnSuccessListener{
                val uploadPictureReference  = storage.reference.child("user_profil_photo").child(imageName)
                uploadPictureReference.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()

                    if (auth.currentUser != null){
                        val profilMap = hashMapOf<String,Any>()

                        profilMap.put("profilPhotoDownloadUrl",downloadUrl)
                        profilMap.put("userEmail",auth.currentUser!!.email!!)

                        firestore.collection("Profil").add(profilMap).addOnSuccessListener {

                            val intent = Intent(this@ProfilPictureActivity, TimeLineActivity::class.java)
                            finish()
                            startActivity(intent)

                        }.addOnFailureListener {
                            Toast.makeText(this@ProfilPictureActivity, it.localizedMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }.addOnFailureListener{
                Toast.makeText(this@ProfilPictureActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun selectImage(view : View){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view,"Permission needed for gallery!",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission"){
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }.show()
            }
            else{
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        else{
            val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        }
    }

    private fun registerLauncher(){
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->

            if (result.resultCode == RESULT_OK){
                val intentFromResult = result.data

                if (intentFromResult != null){
                    selectedImg = intentFromResult.data
                    selectedImg?.let {
                        binding.imageView12.setImageURI(it)
                    }
                }
            }
        }

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){result ->

            if (result){
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
            else{
                Toast.makeText(this@ProfilPictureActivity, "Permission Needed!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}