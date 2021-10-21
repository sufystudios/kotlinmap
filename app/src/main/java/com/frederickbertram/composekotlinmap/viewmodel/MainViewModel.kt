package com.frederickbertram.composekotlinmap.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.frederickbertram.composekotlinmap.model.JsonItem
import com.frederickbertram.composekotlinmap.view.MapView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class MainViewModel() : ViewModel() {



    init{
        //fetch json data
        fetchJson()

    }

    fun fetchJson() {
        val url="https://github.com/elementengineering/Mobile-App-Coding-Challenge/blob/master/data/data.json"
        val request= Request.Builder().url(url).build()
        val client= OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body= response.body?.string()



                val gson= GsonBuilder().create()
                if (body != null) {
                    Log.d("JSONDATA",body.toString())
                }



            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }


}