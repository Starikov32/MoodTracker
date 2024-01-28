package com.radzhabov.moodtracker.data.network.api.response.responsebody

import com.google.gson.annotations.SerializedName
import com.radzhabov.moodtracker.data.network.api.response.responsebody.ForecastDayResponse

data class ForecastResponse(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayResponse>? = null
)
