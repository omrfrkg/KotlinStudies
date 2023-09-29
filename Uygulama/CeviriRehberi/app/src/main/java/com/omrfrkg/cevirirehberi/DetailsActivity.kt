package com.omrfrkg.cevirirehberi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omrfrkg.cevirirehberi.databinding.ActivityDetailsBinding
import com.omrfrkg.cevirirehberi.databinding.RecyclerRowBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        val selectedTranslate = Singleton.selectedTranslate

        if (selectedTranslate != null){
            binding.wordText.text = selectedTranslate.word + " :"
            binding.translateText.text = selectedTranslate.translateWord
            binding.imageView.setImageResource(selectedTranslate.image)
        }
    }
}