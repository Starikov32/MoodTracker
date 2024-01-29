package com.radzhabov.moodtracker.home.data.network.api.response.responsebody

import com.google.gson.annotations.SerializedName
import com.radzhabov.moodtracker.home.data.network.api.response.responsebody.Condition

data class DayResponse(
    @SerializedName("maxtemp_c") val maxTempC: Float? = null,
    @SerializedName("mintemp_c") val minTempC: Float? = null,
    @SerializedName("condition") val condition: Condition? = null,
)
