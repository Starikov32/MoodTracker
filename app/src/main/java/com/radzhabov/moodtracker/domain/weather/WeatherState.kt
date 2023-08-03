package com.radzhabov.moodtracker.domain.weather

import com.radzhabov.moodtracker.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
