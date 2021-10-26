package com.frederickbertram.composekotlinmap.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneOffset

import java.time.format.DateTimeFormatter
import kotlin.CharSequence as CharSequence1


class MapItems (var typeId: Int, var departureTime: String, var route: String, var name: String, var latitude: Double, var longitude: Double, var isExpress: Boolean, var hasMyKiTopUp: Boolean) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedTime(date: String): String {
        val
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dHH:mm:ssz")
        val subDate = date.subSequence(0..9).toString()
        val subTime= date.subSequence(11..18).toString()
        var formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a")
        var dateTime = LocalDateTime.parse(subDate+subTime+ZoneOffset.UTC,formatter)

        return dateTime.format(formatter2)

    }

}