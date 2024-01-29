package com.radzhabov.moodtracker.home.data.mapper

import com.radzhabov.moodtracker.home.data.network.api.response.WeatherResponse
import com.radzhabov.moodtracker.home.domain.model.CurrentWeatherCardModel
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
