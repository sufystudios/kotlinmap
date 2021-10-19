package com.frederickbertram.composekotlinmap

import android.os.Bundle
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

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeKotlinMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                        //Header("Kotlin Map")

                        MapView(mainViewModel = mainViewModel)

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