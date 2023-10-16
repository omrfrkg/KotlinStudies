package com.omrfrkg.kotlininstagram.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.omrfrkg.kotlininstagram.R
import com.omrfrkg.kotlininstagram.adapter.TimeLineAdapter
import com.omrfrkg.kotlininstagram.databinding.ActivityTimeLineBinding
import com.omrfrkg.kotlininstagram.model.Post
import com.omrfrkg.kotlininstagram.model.Profil
import com.omrfrkg.kotlininstagram.model.Singleton
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class TimeLineActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTimeLineBinding

    private lateinit var auth : FirebaseAuth
    private lateinit var firestore : FirebaseFirestore

    private lateinit var postArrayList : ArrayList<Post>
    private lateinit var timeLineAdapter : TimeLineAdapter
    private lateinit var profilArrayList : ArrayList<Profil>

    private var sayac = 0

    private lateinit var mail : String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTimeLineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        firestore = Firebase.firestore


        postArrayList = ArrayList()
        profilArrayList = ArrayList()


        getData()
        getProfiPhotoData()

        val currentUser = auth.currentUser

        if (currentUser != null){
            mail = currentUser.email.toString()

            for (item in profilArrayList){
                if (mail == item.email){
                    Picasso.get().load(item.photo).into(binding.imageView41)
                }

            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        timeLineAdapter = TimeLineAdapter(postArrayList,profilArrayList,mail)
        binding.recyclerView.adapter = timeLineAdapter






    }

    private fun getData(){
        firestore.collection("Posts").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener{ value, error ->
            if (error != null){
                Toast.makeText(this@TimeLineActivity, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            else{
                if (value != null){
                    if (!value.isEmpty){
                        val documents = value.documents

                        postArrayList.clear()

                        for (document in documents){

                            //casting
                            val comment = document.get("comment") as String
                            val userEmail = document.get("userEmail") as String
                            val downloadUrl = document.get("downloadUrl") as String

                            val post = Post(userEmail,comment,downloadUrl)
                            postArrayList.add(post)
                        }

                        timeLineAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun getProfiPhotoData(){
        firestore.collection("Profil").addSnapshotListener{value, error ->

            if(error != null){
                Toast.makeText(this@TimeLineActivity, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            else{
                if (value != null){
                    if (!value.isEmpty){
                        val profiPhotos = value.documents

                        profilArrayList.clear()

                        for (item in profiPhotos){
                            //cast
                            val img = item.get("profilPhotoDownloadUrl") as String
                            val mail = item.get("userEmail") as String

                            val profil = Profil(img,mail)
                            profilArrayList.add(profil)
                        }
                        timeLineAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    fun postEklemeSayfasinaGit(view : View){
        val intent = Intent(this@TimeLineActivity, PostActivity::class.java)
        startActivity(intent)
    }

    fun cikisYap(view : View){

        auth.signOut()
        val intent = Intent(this@TimeLineActivity, MainActivity::class.java)
        startActivity(intent)

    }
}