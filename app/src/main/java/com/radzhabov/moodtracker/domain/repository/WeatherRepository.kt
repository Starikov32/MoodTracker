package com.radzhabov.moodtracker.domain.repository

import com.radzhabov.moodtracker.data.model.Weather
import org.threeten.bp.LocalDateTime

interface WeatherRepository {
    suspend fun getWeatherFromDb(time: LocalDateTime): Weather?
    suspend fun insertWeatherIntoDb(weather: Weather)
}