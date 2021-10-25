package com.frederickbertram.composekotlinmap

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Observer
import com.frederickbertram.composekotlinmap.ui.theme.ComposeKotlinMapTheme

import com.frederickbertram.composekotlinmap.view.ShowMapView
import com.frederickbertram.composekotlinmap.viewmodel.MainViewModel
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import rememberMapViewWithLifecycle

@InternalCoroutinesApi
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mapView: MapView
        val mainViewModel: MainViewModel by viewModels()
        mainViewModel.setLifecycle(this)


        setContent {
            mapView = rememberMapViewWithLifecycle()
            mainViewModel.setMap(mapView)

            ComposeKotlinMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Header("Kotlin Map")


                    //Thread.sleep(3000)
                    ShowMapView(mainViewModel = mainViewModel, mapView)

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
}