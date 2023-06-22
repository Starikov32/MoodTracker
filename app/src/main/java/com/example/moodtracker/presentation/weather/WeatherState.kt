package com.example.moodtracker.presentation.weather

import com.example.moodtracker.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
