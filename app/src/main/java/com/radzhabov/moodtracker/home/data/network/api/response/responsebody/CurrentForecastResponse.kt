package com.radzhabov.moodtracker.home.data.network.api.response.responsebody

import com.google.gson.annotations.SerializedName

data class CurrentForecastResponse(
    @SerializedName("temp_c") val tempC: Float? = null,
    @SerializedName("condition") val condition: Condition? = null,
)
