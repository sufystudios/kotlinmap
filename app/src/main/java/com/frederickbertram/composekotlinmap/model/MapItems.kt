package com.frederickbertram.composekotlinmap.model

import com.google.gson.GsonBuilder
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class MapItems (var typeId: Int, var departureTime: String, var route: String, var name: String, var latitude: Double, var longitude: Double, var isExpress: Boolean, var hasMyKiTopUp: Boolean) {}
fun createListFromJsonString(json: String) : List<MapItems> {
    val gson = GsonBuilder().create()
    return gson.fromJson(json, Array<MapItems>::class.java).toList()
}
fun getFormattedTime(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dHH:mm:ssz")
    val subDate = date.subSequence(0..9).toString()
    val subTime= date.subSequence(11..18).toString()
    var formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a")
    var dateTime = LocalDateTime.parse(subDate+subTime+ZoneOffset.UTC,formatter)

    return dateTime.format(formatter2)

}

fun getTrams(items :List<MapItems>): List<MapItems> {
    return items.filter { v -> v.typeId==1 }
}

fun getTrains(items :List<MapItems>): List<MapItems> {
    return items.filter { v -> v.typeId==0 }
}