package com.radzhabov.moodtracker.data.network.api

import com.radzhabov.moodtracker.data.network.dto.WeatherDto
import com.radzhabov.moodtracker.domain.util.Constants.Companion.WEATHER_ENDPOINT
import com.radzhabov.moodtracker.domain.util.Constants.Companion.latitude
import com.radzhabov.moodtracker.domain.util.Constants.Companion.longitude
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(WEATHER_ENDPOINT)
    suspend fun getWeatherData(
        @Query(latitude) lat: Double,
        @Query(longitude) long: Double,
    ): WeatherDto
}
