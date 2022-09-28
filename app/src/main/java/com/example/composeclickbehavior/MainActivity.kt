package com.example.composeclickbehavior

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeclickbehavior.MainActivity.Companion.count
import com.example.composeclickbehavior.MainActivity.Companion.squeezeTimes
import com.example.composeclickbehavior.ui.theme.ComposeClickBehaviorTheme
import java.lang.Math.random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeClickBehaviorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonScreen()
                }
            }
        }
    }

    companion object{
        var count = 0
        var squeezeTimes = (random() * 4).toInt()
    }
}


@Composable
fun LemonScreen(){

    var lemonStep by remember { mutableStateOf(0)}
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LemonText(lemonStep)
        LemonImage(lemonStep){
            lemonStep = ClickBehavior(lemonStep)
        }
    }
}


fun ClickBehavior(lemonStep: Int):Int {

    Log.d("count", "${count}")
    Log.d("lemonStep", "${lemonStep}")

    return when(lemonStep){
        0 -> {
            1
        }
        1 -> {
            if (count < MainActivity.squeezeTimes){
                count++
                1
            } else {
                2
            }
        }
        2 -> {
            3
        }
        else -> {
            count = 0
            MainActivity.squeezeTimes = (random() * 4).toInt()
            0
        }
    }
}

@Composable
fun LemonText(lemonStep: Int){
    var lemonTexts = listOf(
        R.string.lemon_tree,
        R.string.lemon,
        R.string.glass_of_lemon,
        R.string.empty_glass
    )

    Text(
        text = stringResource(lemonTexts[lemonStep])
    )

}

@Composable
fun LemonImage(
    lemonStep: Int,
    OnTap: () -> Unit) {
    var lemonImages = listOf(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )

    Image(
        painter = painterResource(id = lemonImages[lemonStep]),
        contentDescription = null,
        modifier = Modifier
            .clickable {
                OnTap()
            }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeClickBehaviorTheme {
        LemonScreen()
    }
}