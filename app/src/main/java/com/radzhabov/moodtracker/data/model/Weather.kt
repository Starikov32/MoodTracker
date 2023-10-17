package com.radzhabov.moodtracker.data.model

import com.radzhabov.moodtracker.domain.weather.WeatherType
import org.threeten.bp.LocalDateTime

data class Weather(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType,
)