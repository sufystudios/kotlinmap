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
import com.google.android.libraries.maps.MapView
import kotlinx.coroutines.InternalCoroutinesApi
import rememberMapViewWithLifecycle

@InternalCoroutinesApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mapView: MapView
        val mainViewModel: MainViewModel by viewModels()
        mainViewModel.setLifecycle(this)
        //mainViewModel.fetchJson()
        setContent {
            mapView = rememberMapViewWithLifecycle()
            mainViewModel.setMap(mapView)

            ComposeKotlinMapTheme {
                Surface(color = MaterialTheme.colors.background) {

                    Header("Kotlin Map")

                    ShowMapView(mapView, mainViewModel.feed)
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