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

import com.frederickbertram.composekotlinmap.view.ShowMapView
import com.frederickbertram.composekotlinmap.viewmodel.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okhttp3.internal.notify
import okhttp3.internal.wait

import rememberMapViewWithLifecycle
import java.util.*

@InternalCoroutinesApi
class MainActivity : ComponentActivity() {


    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = MainViewModel()
        mainViewModel.fetchJson()
        var lock = ""


        setContent {
            ComposeKotlinMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Header("Kotlin Map")



                    //Thread.sleep(3000)
                    ShowMapView(mainViewModel = mainViewModel)
                       
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