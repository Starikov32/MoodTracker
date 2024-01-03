package com.radzhabov.moodtracker.data.mapper

import com.radzhabov.moodtracker.data.network.api.response.WeatherResponse
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import kotlin.math.floor

fun WeatherResponse.toCurrentCardWeather() = CurrentWeatherCardModel(
    nameCity = location?.name,
    dateTime = location?.localtime,
    conditionText = current?.condition?.text,
    currentTemp = current?.tempC?.let { it1 -> floor(it1) },
    maxTemp = forecast?.forecastDay?.first()?.day?.maxTempC?.let { it1 -> floor(it1) },
    minTemp = forecast?.forecastDay?.first()?.day?.minTempC?.let { it1 -> floor(it1) },
    imageUrl = current?.condition?.icon
)
