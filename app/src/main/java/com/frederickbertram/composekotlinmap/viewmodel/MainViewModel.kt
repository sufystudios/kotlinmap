package com.frederickbertram.composekotlinmap.viewmodel

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.frederickbertram.composekotlinmap.model.MapItems
import com.frederickbertram.composekotlinmap.model.createListFromJsonString
import com.google.android.gms.common.config.GservicesValue.init
import com.google.android.libraries.maps.MapView
import okhttp3.*
import java.io.IOException

class MainViewModel : ViewModel() {
    internal lateinit var mapView: MapView

    private lateinit var owner: ComponentActivity

    //live data isn't used for this static map so I didn't implement an observer for change yet
    var feedStore = listOf<MapItems>()
    var feed = mutableStateListOf<MapItems>().also { fetchJson() }


    fun removeAllFeed() {
        feed.clear()
        feed.addAll(feedStore)

    }
    fun addAllFeed(l :List<MapItems>) {
        feed.clear()
        feed.addAll(l)
    }


    fun fetchJson() {
        val url =
            "https://raw.githubusercontent.com/elementengineering/Mobile-App-Coding-Challenge/master/data/data.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                if (body != null) {
                    removeAllFeed()
                    feedStore = createListFromJsonString(body)
                    addAllFeed(feedStore)

                    for(item in feed) {
                        Log.d("JSONITEM", item.name)
                    }
                    Log.d("JSONDATA", body.toString())
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

    fun setLifecycle(owner: ComponentActivity) {
        this.owner = owner
    }

    fun setMap(mapView: MapView) {
        this.mapView = mapView
    }
}
