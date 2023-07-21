package com.radzhabov.moodtracker.data.network.api

import com.radzhabov.moodtracker.data.network.dto.WeatherDto
import com.radzhabov.moodtracker.domain.util.Constants.Companion.WEATHER_ENDPOINT
import com.radzhabov.moodtracker.domain.util.Constants.Companion.LATITUDE
import com.radzhabov.moodtracker.domain.util.Constants.Companion.LONGITUDE
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(WEATHER_ENDPOINT)
    suspend fun getWeatherData(
        @Query(LATITUDE) lat: Double,
        @Query(LONGITUDE) long: Double,
    ): WeatherDto
}
