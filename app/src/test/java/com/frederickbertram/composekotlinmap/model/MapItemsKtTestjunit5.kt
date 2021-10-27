package com.frederickbertram.composekotlinmap.model

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import com.google.gson.GsonBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
@Execution(ExecutionMode.SAME_THREAD)
internal class MapItemsKtTestjunit5 {
    lateinit var mapItems: List<MapItems>
    val json = "[\n" +
            "    {\n" +
            "        \"typeId\": 0,\n" +
            "        \"departureTime\": \"2021-07-03T09:10:00.000Z\",\n" +
            "        \"name\": \"Flinders\",\n" +
            "        \"latitude\": -37.8181755,\n" +
            "        \"longitude\": 144.9661256 },\n" +
            "    {\n" +
            "        \"typeId\": 0,\n" +
            "        \"departureTime\": \"2021-07-03T14:30:00.000Z\",\n" +
            "        \"name\": \"Flinders\",\n" +
            "        \"latitude\": -37.8181755,\n" +
            "        \"longitude\": 144.9661256,\n" +
            "        \"isExpress\": true },\n" +
            "    {\n" +
            "        \"typeId\": 0,\n" +
            "        \"departureTime\": \"2021-07-04T10:15:00.000Z\",\n" +
            "        \"name\": \"North Melbourne\",\n" +
            "        \"latitude\": -37.8068073,\n" +
            "        \"longitude\": 144.9404548,\n" +
            "        \"isExpress\": false },\n" +
            "    {\n" +
            "        \"typeId\": 0,\n" +
            "        \"departureTime\": \"2021-07-04T15:35:00.000Z\",\n" +
            "        \"name\": \"Upfield\",\n" +
            "        \"latitude\": -37.5856691,\n" +
            "        \"longitude\": 145.2270446,\n" +
            "        \"isExpress\": true },\n" +
            "    {\n" +
            "        \"typeId\": 0,\n" +
            "        \"departureTime\": \"2021-07-05T11:20:00.000Z\",\n" +
            "        \"name\": \"Glen Waverley\",\n" +
            "        \"latitude\": -37.8794913,\n" +
            "        \"longitude\": 145.1598501,\n" +
            "        \"isExpress\": false },\n" +
            "    {\n" +
            "        \"typeId\": 0,\n" +
            "        \"departureTime\": \"2021-07-05T16:40:00.000Z\",\n" +
            "        \"name\": \"Ringwood\",\n" +
            "        \"latitude\": -37.8153668,\n" +
            "        \"longitude\": 145.2269614,\n" +
            "        \"isExpress\": false },\n" +
            "    {\n" +
            "        \"typeId\": 0,\n" +
            "        \"departureTime\": \"2021-07-06T12:45:00.000Z\",\n" +
            "        \"name\": \"Frankston\",\n" +
            "        \"latitude\": -38.1429773,\n" +
            "        \"longitude\": 145.1238214,\n" +
            "        \"isExpress\": true },\n" +
            "    {\n" +
            "        \"typeId\": 0,\n" +
            "        \"departureTime\": \"2021-07-06T17:45:00.000Z\",\n" +
            "        \"name\": \"Werribee\",\n" +
            "        \"latitude\": -37.8985846,\n" +
            "        \"longitude\": 144.6590184,\n" +
            "        \"isExpress\": true },\n" +
            "    {\n" +
            "        \"typeId\": 1,\n" +
            "        \"departureTime\": \"2021-07-07T13:50:00.000Z\",\n" +
            "        \"route\": \"624\",\n" +
            "        \"name\": \"Queen Victoria Market\",\n" +
            "        \"latitude\": -37.806718,\n" +
            "        \"longitude\": 144.9574589,\n" +
            "        \"hasMyKiTopUp\": true },\n" +
            "    {\n" +
            "        \"typeId\": 1,\n" +
            "        \"departureTime\": \"2021-07-07T18:50:00.000Z\",\n" +
            "        \"route\": \"625\",\n" +
            "        \"name\": \"Carlton Gardens\",\n" +
            "        \"latitude\": -37.8049684,\n" +
            "        \"longitude\": 144.9572112,\n" +
            "        \"hasMyKiTopUp\": true },\n" +
            "    {\n" +
            "        \"typeId\": 1,\n" +
            "        \"departureTime\": \"2021-07-08T14:55:00.000Z\",\n" +
            "        \"route\": \"820\",\n" +
            "        \"name\": \"Chadstone Shopping Centre\",\n" +
            "        \"latitude\": -37.8862515,\n" +
            "        \"longitude\": 145.0807788,\n" +
            "        \"hasMyKiTopUp\": true },\n" +
            "    {\n" +
            "        \"typeId\": 1,\n" +
            "        \"departureTime\": \"2021-07-09T19:55:00.000Z\",\n" +
            "        \"route\": \"543\",\n" +
            "        \"name\": \"Monash University\",\n" +
            "        \"latitude\": -37.8771484,\n" +
            "        \"longitude\": 145.0427026,\n" +
            "        \"hasMyKiTopUp\": true },\n" +
            "    {\n" +
            "        \"typeId\": 1,\n" +
            "        \"departureTime\": \"2021-07-08T16:00:00.000Z\",\n" +
            "        \"route\": \"701\",\n" +
            "        \"name\": \"Brighton Beach\",\n" +
            "        \"latitude\": -37.9265405,\n" +
            "        \"longitude\": 144.9868176,\n" +
            "        \"hasMyKiTopUp\": false\n" +
            "    }\n" +
            "]"




    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        mapItems = createListFromJsonString(json)
    }

    @org.junit.jupiter.api.Test
    fun createListFromJsonString() {
        assert(!mapItems.isEmpty())
        assert(mapItems[0].typeId==0 && mapItems[mapItems.size-1].typeId==1)
    }

    @org.junit.jupiter.api.Test
    fun getFormattedTime() {
        Assertions.assertEquals(getFormattedTime(mapItems.get(0).departureTime) ,"03 Jul. 2021 09:10 am")
    }

    @org.junit.jupiter.api.Test
    fun getTrams() {
        val trams = getTrams(mapItems)
        assert(trams.filter { v -> v.typeId == 0 }.isEmpty())
    }

    @org.junit.jupiter.api.Test
    fun getTrains() {
        val train = getTrains(mapItems)
        assert(train.filter { v -> v.typeId == 1 }.isEmpty())
    }
}