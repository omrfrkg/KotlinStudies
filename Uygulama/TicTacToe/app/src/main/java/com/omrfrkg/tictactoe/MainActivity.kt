package com.omrfrkg.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.omrfrkg.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var moveList = arrayListOf<String>("123","147","159","258","357","369","456","789","987")

    private var playerOneMoveList : String = ""
    private var playerTwoMoveList : String = ""

    //Player1 First Move
    private var firstMovePlayerTwo  = false

    private var roundCounter = 0

    private var winScorePlayerOne = 0
    private var winScorePlayerTwo = 0
    private var tieScore = 0
    private var winStatus : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tieTextScore.text = tieScore.toString()
        binding.playerXScoreText.text = winScorePlayerOne.toString()
        binding.playerOTextScore.text = winScorePlayerTwo.toString()

    }

    fun moveOne(view : View){
        if (!firstMovePlayerTwo){
            binding.move1View.setImageResource(R.drawable.new_x)
            playerOneMoveList += "1"
            firstMovePlayerTwo = true
        }
        else{
            binding.move1View.setImageResource(R.drawable.o)
            playerTwoMoveList += "1"
            firstMovePlayerTwo = false
        }
        binding.move1View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    fun moveTwo(view : View){
        if (!firstMovePlayerTwo){
            binding.move2View.setImageResource(R.drawable.new_x)
            playerOneMoveList += "2"
            firstMovePlayerTwo = true
        }
        else{
            binding.move2View.setImageResource(R.drawable.o)
            playerTwoMoveList += "2"
            firstMovePlayerTwo = false
        }

        binding.move2View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    fun moveThree(view : View){
        if (!firstMovePlayerTwo){
            binding.move3View.setImageResource(R.drawable.new_x)
            playerOneMoveList += "3"
            firstMovePlayerTwo = true
        }
        else{
            binding.move3View.setImageResource(R.drawable.o)
            playerTwoMoveList += "3"
            firstMovePlayerTwo = false
        }

        binding.move3View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    fun moveFour(view : View){
        if (!firstMovePlayerTwo){
            binding.move4View.setImageResource(R.drawable.new_x)
            playerOneMoveList += "4"
            firstMovePlayerTwo = true
        }
        else{
            binding.move4View.setImageResource(R.drawable.o)
            playerTwoMoveList += "4"
            firstMovePlayerTwo = false
        }

        binding.move4View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    fun moveFive(view : View){
        if (!firstMovePlayerTwo){
            binding.move5View.setImageResource(R.drawable.new_x)
            //playerOneMoveList.add("5")
            playerOneMoveList += "5"
            firstMovePlayerTwo = true
        }
        else{
            binding.move5View.setImageResource(R.drawable.o)
            //playerTwoMoveList.add("5")
            playerTwoMoveList += "5"
            firstMovePlayerTwo = false
        }

        binding.move5View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    fun moveSix(view : View){
        if (!firstMovePlayerTwo){
            binding.move6View.setImageResource(R.drawable.new_x)
            playerOneMoveList += "6"
            firstMovePlayerTwo = true
        }
        else{
            binding.move6View.setImageResource(R.drawable.o)
            playerTwoMoveList += "6"
            firstMovePlayerTwo = false
        }

        binding.move6View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    fun moveSeven(view : View){
        if (!firstMovePlayerTwo){
            binding.move7View.setImageResource(R.drawable.new_x)
            playerOneMoveList += "7"
            firstMovePlayerTwo = true
        }
        else{
            binding.move7View.setImageResource(R.drawable.o)
            playerTwoMoveList += "7"
            firstMovePlayerTwo = false
        }

        binding.move7View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    fun moveEight(view : View){
        if (!firstMovePlayerTwo){
            binding.move8View.setImageResource(R.drawable.new_x)
            playerOneMoveList += "8"
            firstMovePlayerTwo = true
        }
        else{
            binding.move8View.setImageResource(R.drawable.o)
            playerTwoMoveList += "8"
            firstMovePlayerTwo = false
        }

        binding.move8View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    fun moveNine(view : View){
        if (!firstMovePlayerTwo){
            binding.move9View.setImageResource(R.drawable.new_x)
            playerOneMoveList += "9"
            firstMovePlayerTwo = true
        }
        else{
            binding.move9View.setImageResource(R.drawable.o)
            playerTwoMoveList += "9"
            firstMovePlayerTwo = false
        }

        binding.move9View.isClickable = false

        roundCounter++

        calculateWinner()
    }

    private fun calculateWinner(){
        if (roundCounter >= 4){

            moveList.forEach{


                if(winningMove(playerOneMoveList,it)){
                    Toast.makeText(this@MainActivity,"Kazanan 1.Player!",Toast.LENGTH_LONG).show()
                    winScorePlayerOne++
                    winStatus = true
                    binding.playerXScoreText.text = winScorePlayerOne.toString()
                    newRound()
                    firstMovePlayerTwo = false
                }
                else if (winningMove(playerTwoMoveList,it)){
                    Toast.makeText(this@MainActivity,"Kazanan 2.Player!",Toast.LENGTH_LONG).show()
                    winScorePlayerTwo++
                    winStatus = true
                    binding.playerOTextScore.text = winScorePlayerTwo.toString()
                    newRound()
                    firstMovePlayerTwo = true
                }
            }

            if(!winStatus && roundCounter == 9){
                tieScore++
                binding.tieTextScore.text = tieScore.toString()
                newRound()
                Toast.makeText(this@MainActivity,"Berabere!",Toast.LENGTH_LONG).show()
                firstMovePlayerTwo = false
            }
        }

        if (winScorePlayerOne == 3){
            Toast.makeText(this@MainActivity,"Oyunu 1.Player Kazandı!",Toast.LENGTH_LONG).show()
            winScorePlayerOne = 0
            winScorePlayerTwo = 0

            var intent = intent
            finish()
            startActivity(intent)
        }
        else if(winScorePlayerTwo == 3){
            Toast.makeText(this@MainActivity,"Oyunu 2.Player Kazandı!",Toast.LENGTH_LONG).show()
            winScorePlayerOne = 0
            winScorePlayerTwo = 0

            var intent = intent
            finish()
            startActivity(intent)
        }

    }

    fun winningMove(mainString: String, searchedString: String): Boolean {
        val sortedMainString = mainString.toList().sorted().joinToString("")
        val sortedSearchedString = searchedString.toList().sorted().joinToString("")
        return sortedMainString.contains(sortedSearchedString)
    }

    fun newRound(){

        binding.move1View.setImageResource(0)
        binding.move2View.setImageResource(0)
        binding.move3View.setImageResource(0)
        binding.move4View.setImageResource(0)
        binding.move5View.setImageResource(0)
        binding.move6View.setImageResource(0)
        binding.move7View.setImageResource(0)
        binding.move8View.setImageResource(0)
        binding.move9View.setImageResource(0)

        binding.move1View.isClickable = true
        binding.move2View.isClickable = true
        binding.move3View.isClickable = true
        binding.move4View.isClickable = true
        binding.move5View.isClickable = true
        binding.move6View.isClickable = true
        binding.move7View.isClickable = true
        binding.move8View.isClickable = true
        binding.move9View.isClickable = true

        playerOneMoveList = ""
        playerTwoMoveList = ""

        winStatus = false
        roundCounter  = 0
    }

}