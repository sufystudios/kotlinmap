package com.frederickbertram.composekotlinmap.view

import android.util.Half.toFloat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.frederickbertram.composekotlinmap.viewmodel.MainViewModel
import com.google.android.libraries.maps.CameraUpdate
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.LocationSource
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ShowMapView(mainViewModel: MainViewModel, mapView: MapView) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {

        AndroidView({ mapView }) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                var firstLocation= false
                var zoomLevel = 9
                val zoom = CameraUpdateFactory.zoomTo(zoomLevel.toFloat())
                map.moveCamera(zoom)
                map.uiSettings.isZoomControlsEnabled = true

                for(item in mainViewModel.feed.value!!) {
                    val marker =MarkerOptions()
                    val position = LatLng(item.latitude,item.longitude)
                    val cameraUpdate = CameraUpdateFactory.newLatLng(position)

                    if(!firstLocation){
                        firstLocation=true
                        map.moveCamera(cameraUpdate)

                    }
                    marker.title(item.name + " " + if(item.typeId==0)"(Train)" else "(Tram)" )
                    marker.snippet(item.departureTime)
                    marker.position(position)
                    map.addMarker(marker)



                }


            }}
    }}






