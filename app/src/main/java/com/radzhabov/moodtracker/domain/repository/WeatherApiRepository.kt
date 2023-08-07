package com.radzhabov.moodtracker.domain.repository

import com.radzhabov.moodtracker.domain.util.Resource
import com.radzhabov.moodtracker.domain.weather.WeatherInfo

interface WeatherApiRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}