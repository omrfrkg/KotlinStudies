package com.omrfrkg.basiccalculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.omrfrkg.basiccalculatorkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var firstNumber : Float? = null
    private var secondNumber : Float? = null
    private var result : Float? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






    }

    fun sum(view : View){

        firstNumber = binding.numberOneText.text.toString().toFloatOrNull()
        secondNumber = binding.numberTwoText.text.toString().toFloatOrNull()


        if (firstNumber == null || secondNumber == null){
            binding.result.text = "Enter Numbers!"
        }
        else{
            result = firstNumber!! + secondNumber!!
            binding.result.text = "Result : $result"
        }

    }

    fun minus(view : View){
        firstNumber = binding.numberOneText.text.toString().toFloatOrNull()
        secondNumber = binding.numberTwoText.text.toString().toFloatOrNull()

        if (firstNumber == null || secondNumber == null){
            binding.result.text = "Enter Numbers!"
        }
        else{
            result = firstNumber!! - secondNumber!!
            binding.result.text = "Result : $result"
        }
    }

    fun multiply(view :View){

        firstNumber = binding.numberOneText.text.toString().toFloatOrNull()
        secondNumber = binding.numberTwoText.text.toString().toFloatOrNull()

        if (firstNumber == null || secondNumber == null){
            binding.result.text = "Enter Numbers!"
        }
        else{
            result = firstNumber!! * secondNumber!!
            binding.result.text = "Result : $result"
        }
    }

    fun divide(view :View){
       firstNumber = binding.numberOneText.text.toString().toFloatOrNull()
       secondNumber = binding.numberTwoText.text.toString().toFloatOrNull()

        if (firstNumber == null || secondNumber == null){
            binding.result.text = "Enter Numbers!"
        }
        else{
            result = firstNumber!! / secondNumber!!
            var resultFloat : Float = result!!.toFloat()
            binding.result.text = "Result : $resultFloat"
        }
    }

}

