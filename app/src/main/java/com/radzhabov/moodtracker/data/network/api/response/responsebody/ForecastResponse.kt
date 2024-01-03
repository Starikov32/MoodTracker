package com.example.weatherapp.data.model.response.responsebody

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayResponse>? = null
)