package com.radzhabov.moodtracker.domain.repository

import com.radzhabov.moodtracker.domain.util.Resource
import com.radzhabov.moodtracker.domain.weather.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherApiRepository {
    fun getWeatherData(lat: Double, long: Double): Flow<Resource<WeatherInfo>>
}