package com.frederickbertram.composekotlinmap.model

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import androidx.lifecycle.MutableLiveData
import java.io.IOException

class MapItems (var typeId: Int, var departureTime: String, var route: String, var name: String, var latitude: Double, var longitude: Double, var isExpress: Boolean, var hasMyKiTopUp: Boolean){
}