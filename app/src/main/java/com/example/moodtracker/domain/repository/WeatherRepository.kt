package com.example.moodtracker.domain.repository

import com.example.moodtracker.domain.util.Resource
import com.example.moodtracker.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}
