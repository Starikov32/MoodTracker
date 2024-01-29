package com.radzhabov.moodtracker.home.data.network.dto

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto,
)
