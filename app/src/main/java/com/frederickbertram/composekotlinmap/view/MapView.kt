package com.frederickbertram.composekotlinmap.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.frederickbertram.composekotlinmap.viewmodel.MainViewModel
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.libraries.maps.model.PolylineOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rememberMapViewWithLifecycle
import java.lang.Thread.sleep

@Composable
fun ShowMapView( mainViewModel: MainViewModel) {
    val mapView = rememberMapViewWithLifecycle()
    var feed =mainViewModel.feed


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
        ) {

            AndroidView({ mapView }) { mapView ->
                CoroutineScope(Dispatchers.Main).launch {
                    val map = mapView.awaitMap()
                    map.uiSettings.isZoomControlsEnabled = true


                            for (item in feed) {
                                Log.d("position", item.latitude.toString() + " " + item.longitude.toString())
                                val position = LatLng(item.latitude, item.longitude)
                                val markerOptions = MarkerOptions()
                                    .title(item.name)
                                    .position(position)
                                map.addMarker(markerOptions)
                            }
                        }


                    }
                }}





