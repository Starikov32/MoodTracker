package com.radzhabov.moodtracker.home.domain.model

data class CurrentWeatherCardModel(
    val nameCity: String?,
    val dateTime: String?,
    val conditionText: String?,
    val maxTemp: Float?,
    val minTemp: Float?,
    val currentTemp: Float?,
    val imageUrl: String?,
)
