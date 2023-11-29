package com.omrfrkg.composeintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omrfrkg.composeintro.ui.theme.ComposeIntroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeIntroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }

                /*testFunction(5,::testFunctionLambda)

                //Farklı gösterimleri(Eğer son parametresi bu durumdaysa)
                testFunction(5){
                    testFunctionLambda()
                }

                 */
            }
        }
    }

   /* fun testFunction(int : Int,myFunction: () -> Unit){
        myFunction.invoke()
    }

    fun testFunctionLambda(){
        println("test")
    }

    */
}

@Composable
fun MainScreen(){

    //Column, Row, Box

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
    //verticalArrangement = Arrangement.Center) {
    //verticalArrangement = Arrangement.SpaceAround) {
    //verticalArrangement = Arrangement.SpaceBetween) {
    //verticalArrangement = Arrangement.SpaceEvenly,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {

        CustomText(text = "Hello Android!")
        Spacer(modifier = Modifier.padding(5.dp))
        CustomText(text = "Hello World!")
        Spacer(modifier = Modifier.padding(5.dp))
        CustomText(text = "Hello Kotlin!")
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomText(text = "Test 1")
            CustomText(text = "Test 2")
            CustomText(text = "Test 3")
        }


    }
}

@Composable
fun CustomText(text: String){
    Text(
        modifier = Modifier
            .clickable {
                println("Hello Android!")
            }
            .background(Color.Black)
            .padding(top = 10.dp, start = 1.dp, end = 1.dp, bottom = 30.dp),
            //.padding(5.dp),
            //.width(150.dp),
            //.height(100.dp)
            //.fillMaxWidth()
            //.fillMaxHeight()
            //.fillMaxSize(0.5f),
        text = text,
        color = Color.White,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    ComposeIntroTheme {
        MainScreen()
    }
}