package com.omrfrkg.cevirirehberi

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.omrfrkg.cevirirehberi.databinding.ActivityMainBinding
import com.omrfrkg.cevirirehberi.databinding.ActivityStartBinding
import org.intellij.lang.annotations.Language

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var translateList : ArrayList<Translate>
    private var language: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        translateList = ArrayList<Translate>()


        val getIntent = intent
        language = getIntent.getStringExtra("language")


        //Data

        englishData()
        germanyData()
        spanishData()
        italianoData()


        if (language != null){
            val translateAdapter = TranslateAdapter(translateList.filter {it.language == language} as ArrayList<Translate>)
            binding.recyclerView.adapter = translateAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }


    }

    fun englishData(){
        val word = Translate("the","o","English", R.drawable.england)
        val word2 = Translate("be","olmak","English",R.drawable.england)
        val word3 = Translate("to","için, -e, -a, -ye","English",R.drawable.england)
        val word4 = Translate("of","nin, -ın, -ün, -ün, -nın","English",R.drawable.england)
        val word5 = Translate("and","ve","English",R.drawable.england)
        val word6 = Translate("a","bir","English",R.drawable.england)
        val word7 = Translate("in","içinde","English",R.drawable.england)
        val word8 = Translate("that","ki, şu, o","English",R.drawable.england)
        val word9 = Translate("have","sahip olmak","English",R.drawable.england)
        val word10 = Translate("I","ben","English",R.drawable.england)
        val word11 = Translate("it","o, bu","English",R.drawable.england)
        val word12 = Translate("for","için","English",R.drawable.england)
        val word13 = Translate("not","değil","English",R.drawable.england)
        val word14 = Translate("on","üstünde, üzerinde","English",R.drawable.england)
        val word15 = Translate("with","ile","English",R.drawable.england)
        val word16 = Translate("as","olarak,gibi","English",R.drawable.england)
        val word17 = Translate("he","o (erkek)","English",R.drawable.england)
        val word18 = Translate("you","sen, siz","English",R.drawable.england)
        val word19 = Translate("do","yapmak","English",R.drawable.england)
        val word20 = Translate("at","-de, -da, -te, -ta","English",R.drawable.england)

        val wordList = arrayListOf<Translate>(word,word2,word3,word4,word5,word6,word7,word8,word9,
            word10,word11,word12,word13,word14,word15,word16,word17,word18,word19,word20)
        wordList.forEach{item->
            translateList.add(item)
        }
    }

    fun germanyData(){
        val word = Translate("der","o","Deutsch",R.drawable.germany)
        val word2 = Translate("und","ve","Deutsch",R.drawable.germany)
        val word3 = Translate("in","içinde, -de, -da","Deutsch",R.drawable.germany)
        val word4 = Translate("den","onu ,o","Deutsch",R.drawable.germany)
        val word5 = Translate("von","-den, -dan, -nin","Deutsch",R.drawable.germany)
        val word6 = Translate("zu","-e, -a, -ye, -ya","Deutsch",R.drawable.germany)
        val word7 = Translate("das","şu, bu","Deutsch",R.drawable.germany)
        val word8 = Translate("mit","ile","Deutsch",R.drawable.germany)
        val word9 = Translate("sich","kendisi, kendileri","Deutsch",R.drawable.germany)
        val word10 = Translate("des","nin, -ın, -ün, -ün","Deutsch",R.drawable.germany)
        val word11 = Translate("auf","üzerinde, üstünde","Deutsch",R.drawable.germany)
        val word12 = Translate("für","için","Deutsch",R.drawable.germany)
        val word13 = Translate("ist","-dir, -dır, -tir, -tur","Deutsch",R.drawable.germany)
        val word14 = Translate("im","içinde","Deutsch",R.drawable.germany)
        val word15 = Translate("dem ","-e, -a","Deutsch",R.drawable.germany)
        val word16 = Translate("nicht ","değil","Deutsch",R.drawable.germany)
        val word17 = Translate("ein","bir","Deutsch",R.drawable.germany)
        val word18 = Translate("als","olarak","Deutsch",R.drawable.germany)
        val word19 = Translate("auch","ayrıca, da","Deutsch",R.drawable.germany)
        val word20 = Translate("doch ","ancak","Deutsch",R.drawable.germany)

        val wordList = arrayListOf<Translate>(word,word2,word3,word4,word5,word6,word7,word8,word9,
            word10,word11,word12,word13,word14,word15,word16,word17,word18,word19,word20)
        wordList.forEach{item->
            translateList.add(item)
        }
    }

    fun spanishData(){
        val word = Translate("el","erkek","Spanisch",R.drawable.spain)
        val word2 = Translate("de","nin, -ın, -ün, -ün","Spanisch",R.drawable.spain)
        val word3 = Translate("que","ki, şu, o","Spanisch",R.drawable.spain)
        val word4 = Translate("y","ve","Spanisch",R.drawable.spain)
        val word5 = Translate("a","-e, -a, -ye, -ya","Spanisch",R.drawable.spain)
        val word6 = Translate("per","için","Spanisch",R.drawable.spain)
        val word7 = Translate("un","bir","Spanisch",R.drawable.spain)
        val word8 = Translate("no","hayır, değil","Spanisch",R.drawable.spain)
        val word9 = Translate("haber","varlık, sahip olma","Spanisch",R.drawable.spain)
        val word10 = Translate("con","ile","Spanisch",R.drawable.spain)
        val word11 = Translate("su","onun, -nin","Spanisch",R.drawable.spain)
        val word12 = Translate("para","için","Spanisch",R.drawable.spain)
        val word13 = Translate("como","gibi","Spanisch",R.drawable.spain)
        val word14 = Translate("estar","olmak","Spanisch",R.drawable.spain)
        val word15 = Translate("tener","sahip olmak","Spanisch",R.drawable.spain)
        val word16 = Translate("le","ona","Spanisch",R.drawable.spain)
        val word17 = Translate("lo","onu","Spanisch",R.drawable.spain)
        val word18 = Translate("fue","idi","Spanisch",R.drawable.spain)
        val word19 = Translate("ver","görmek","Spanisch",R.drawable.spain)
        val word20 = Translate("este","bu","Spanisch",R.drawable.spain)

        val wordList = arrayListOf<Translate>(word,word2,word3,word4,word5,word6,word7,word8,word9,
            word10,word11,word12,word13,word14,word15,word16,word17,word18,word19,word20)
        wordList.forEach{item->
            translateList.add(item)
        }
    }

    fun italianoData(){
        val word = Translate("di","-nin, -ın, -ün, -ün","Italiano",R.drawable.italy)
        val word2 = Translate("e","ve","Italiano",R.drawable.italy)
        val word3 = Translate("il","erkek","Italiano",R.drawable.italy)
        val word4 = Translate("in","içinde, -de, -da, -te, -ta","Italiano",R.drawable.italy)
        val word5 = Translate("che","ki, şu, o","Italiano",R.drawable.italy)
        val word6 = Translate("a","-e, -a, -ye, -ya","Italiano",R.drawable.italy)
        val word7 = Translate("per","için","Italiano",R.drawable.italy)
        val word8 = Translate("un","bir","Italiano",R.drawable.italy)
        val word9 = Translate("non","değil","Italiano",R.drawable.italy)
        val word10 = Translate("è","-dir, -dır","Italiano",R.drawable.italy)
        val word11 = Translate("mi","bana","Italiano",R.drawable.italy)
        val word12 = Translate("sono","-dir, -dır, -tir, -tur","Italiano",R.drawable.italy)
        val word13 = Translate("lo","onu","Italiano",R.drawable.italy)
        val word14 = Translate("ha","sahip","Italiano",R.drawable.italy)
        val word15 = Translate("con","ile","Italiano",R.drawable.italy)
        val word16 = Translate("da","-den, -dan, -nin, -ın","Italiano",R.drawable.italy)
        val word17 = Translate("come","gibi","Italiano",R.drawable.italy)
        val word18 = Translate("tu","sen","Italiano",R.drawable.italy)
        val word19 = Translate("si","evet, kendisi","Italiano",R.drawable.italy)
        val word20 = Translate("ma","ama, fakat","Italiano",R.drawable.italy)

        val wordList = arrayListOf<Translate>(word,word2,word3,word4,word5,word6,word7,word8,word9,
            word10,word11,word12,word13,word14,word15,word16,word17,word18,word19,word20)
        wordList.forEach{item->
            translateList.add(item)
        }
    }
}