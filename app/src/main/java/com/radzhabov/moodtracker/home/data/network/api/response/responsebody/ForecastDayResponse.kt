package com.radzhabov.moodtracker.home.data.network.api.response.responsebody

import com.example.weatherapp.data.model.response.responsebody.DayResponse
import com.google.gson.annotations.SerializedName

data class ForecastDayResponse(
    @SerializedName("date") val date: String? = null,
    @SerializedName("day") val day: DayResponse? = null,
    @SerializedName("hour") val hour: List<HourResponse>? = null
)
