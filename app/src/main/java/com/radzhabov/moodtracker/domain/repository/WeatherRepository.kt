package com.radzhabov.moodtracker.domain.repository

import com.radzhabov.moodtracker.data.dto.Mood
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherData(city: String): Flow<CurrentWeatherCardModel?>

    suspend fun getMood(name: String): Mood?

    suspend fun insertMoodCriteria(mood: Mood)
}