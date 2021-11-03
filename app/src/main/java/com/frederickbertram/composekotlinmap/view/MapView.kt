package com.frederickbertram.composekotlinmap.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import com.frederickbertram.composekotlinmap.model.MapItems
import com.frederickbertram.composekotlinmap.model.getFormattedTime
import com.frederickbertram.composekotlinmap.model.getTrains
import com.frederickbertram.composekotlinmap.viewmodel.MainViewModel
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MapScreen(mapView: MapView, mainViewModel: MainViewModel) {
    ShowMapView( mapView, mainViewModel.feed, mainViewModel::addAllFeed, mainViewModel::removeAllFeed)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowMapView(
    mapView: MapView,
    feed: List<MapItems>,
    addList: (List<MapItems>) -> Unit,
    clearList :() -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)

    ) {

        Text(
            modifier = Modifier
                .height(Dp(40F))
                .fillMaxWidth(), textAlign = TextAlign.Center, text = "\nDepartures"
        )
        Row {
            Button(onClick = {
                clearList()
                for(i in feed) {
                    if(i.typeId==1) {
                        i.marker.visible(false)
                    } else {
                        i.marker.visible(true)
                    }

                }
            }) {
                Text(text = "Filter Trains")
            }            
        }
        AndroidView({ mapView }) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                var zoomLevel = 8
                val zoom = CameraUpdateFactory.zoomTo(zoomLevel.toFloat())
                map.moveCamera(zoom)
                map.uiSettings.isZoomControlsEnabled = true

                for (item in feed) {
                    val marker = MarkerOptions()
                    val position = LatLng(item.latitude, item.longitude)
                    val cameraUpdate = CameraUpdateFactory.newLatLng(position)
                    map.moveCamera(cameraUpdate)
                    marker.title(item.name + " " + if(item.typeId==0)"(Train)" else "(Tram)" )
                    marker.snippet(getFormattedTime(item.departureTime))
                    marker.position(position)
                    item.marker=marker
                    map.addMarker(marker)
                }
            }
        }
    }
}






