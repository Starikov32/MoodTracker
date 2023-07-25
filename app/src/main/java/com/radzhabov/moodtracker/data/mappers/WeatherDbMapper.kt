package com.radzhabov.moodtracker.data.mappers

import com.radzhabov.moodtracker.data.db.entities.WeatherEntity
import com.radzhabov.moodtracker.data.model.Weather

fun WeatherEntity.mapToWeather() = Weather (
    time = this.time,
    temperatureCelsius = this.temperatureCelsius,
    pressure = this.pressure,
    windSpeed = this.windSpeed,
    humidity = this.humidity,
    weatherType = this.weatherType
)

fun Weather.mapToWeatherEntity() = WeatherEntity(
    time = this.time,
    temperatureCelsius = this.temperatureCelsius,
    pressure = this.pressure,
    windSpeed = this.windSpeed,
    humidity = this.humidity,
    weatherType = this.weatherType
)