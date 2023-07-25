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
) {
    companion object{
        fun defaultWeather() = Weather(time = LocalDateTime.now(), temperatureCelsius = 0.0,
            pressure = 0.0, windSpeed = 0.0, humidity = 0.0, weatherType = WeatherType.Sunny)
    }
}
