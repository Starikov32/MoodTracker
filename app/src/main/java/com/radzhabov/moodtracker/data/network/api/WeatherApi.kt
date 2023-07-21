package com.radzhabov.moodtracker.data.network.api

import com.radzhabov.moodtracker.data.network.dto.WeatherDto
import com.radzhabov.moodtracker.domain.util.Constants.Companion.WEATHER_ENDPOINT
import com.radzhabov.moodtracker.domain.util.Constants.Companion.LATITUDE_PARAM
import com.radzhabov.moodtracker.domain.util.Constants.Companion.LONGITUDE_PARAM
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(WEATHER_ENDPOINT)
    suspend fun getWeatherData(
        @Query(LATITUDE_PARAM) lat: Double,
        @Query(LONGITUDE_PARAM) long: Double,
    ): WeatherDto
}
