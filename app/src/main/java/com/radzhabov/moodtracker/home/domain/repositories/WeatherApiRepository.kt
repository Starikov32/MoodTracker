package com.radzhabov.moodtracker.home.domain.repositories

import com.radzhabov.moodtracker.home.domain.model.CurrentWeatherCardModel
import kotlinx.coroutines.flow.Flow

interface WeatherApiRepository {
    fun getWeatherData(city: String): Flow<CurrentWeatherCardModel>
}
