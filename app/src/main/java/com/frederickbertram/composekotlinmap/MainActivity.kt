package com.frederickbertram.composekotlinmap

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.frederickbertram.composekotlinmap.ui.theme.ComposeKotlinMapTheme
import com.frederickbertram.composekotlinmap.view.MapView
import com.frederickbertram.composekotlinmap.viewmodel.MainViewModel
import rememberMapViewWithLifecycle
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
val time = java.util.Calendar.getInstance().time


        val sdf = SimpleDateFormat("hh:mm:ss")

        Log.d(" C DATE is  ", sdf.format(time))
        setContent {
            ComposeKotlinMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                        Header("Kotlin Map - time is - " + sdf.format(time))

                        //MapView(mainViewModel = mainViewModel)

                }
            }
        }
    }
}

@Composable
fun Header(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeKotlinMapTheme {
        Header("Android")
    }
}