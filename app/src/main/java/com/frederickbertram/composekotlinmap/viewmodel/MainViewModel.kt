package com.frederickbertram.composekotlinmap.viewmodel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import com.frederickbertram.composekotlinmap.model.MapItems
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.gson.GsonBuilder
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.internal.notifyAll
import rememberMapViewWithLifecycle
import java.io.IOException


class MainViewModel() : ViewModel() {
    var feed :List<MapItems>



    init{
        //fetch json data
        feed= emptyList()


    }


    fun fetchJson() {
        val url="https://raw.githubusercontent.com/elementengineering/Mobile-App-Coding-Challenge/master/data/data.json"
        val request= Request.Builder().url(url).build()
        val client= OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body= response.body?.string()
                val gson = GsonBuilder().create()

                if (body != null) {
                    feed = gson.fromJson(body,Array<MapItems>::class.java).toList()

                    Log.d("JSONDATA",body.toString())
                    Log.d("JSONITEM", feed!![1].name)

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }




}
