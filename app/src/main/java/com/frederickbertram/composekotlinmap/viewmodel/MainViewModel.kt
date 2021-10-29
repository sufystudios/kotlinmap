package com.frederickbertram.composekotlinmap.viewmodel

import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frederickbertram.composekotlinmap.model.MapItems
import com.frederickbertram.composekotlinmap.model.createListFromJsonString
import com.google.android.libraries.maps.MapView
import okhttp3.*
import java.io.IOException

class MainViewModel : ViewModel() {
    internal lateinit var mapView: MapView

    private lateinit var owner: ComponentActivity

    //live data isn't used for this static map so I didn't implement an observer for change yet
    internal val feed: MutableLiveData<List<MapItems>> by lazy {
        MutableLiveData<List<MapItems>>().also { fetchJson() }
    }

    init{
        feed.value= emptyList()
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
                    feed.postValue( createListFromJsonString(body))
                    //Log.d("JSONDATA", body.toString())
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
