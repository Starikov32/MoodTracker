package com.radzhabov.moodtracker.domain.repositories

import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import kotlinx.coroutines.flow.Flow

interface WeatherApiRepository {
    suspend fun getCurrentWeather(city: String): Flow<CurrentWeatherCardModel>
}