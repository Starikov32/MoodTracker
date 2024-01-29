package com.radzhabov.moodtracker.home.data.network.api.response

import com.radzhabov.moodtracker.home.data.network.api.response.responsebody.CurrentForecastResponse
import com.radzhabov.moodtracker.home.data.network.api.response.responsebody.ForecastResponse
import com.radzhabov.moodtracker.home.data.network.api.response.responsebody.LocationResponse
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val location: LocationResponse? = null,
    @SerializedName("current") val current: CurrentForecastResponse? = null,
    @SerializedName("forecast") val forecast: ForecastResponse? = null,
)
