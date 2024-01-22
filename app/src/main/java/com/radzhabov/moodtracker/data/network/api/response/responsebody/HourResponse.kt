package com.radzhabov.moodtracker.data.network.api.response.responsebody

import com.google.gson.annotations.SerializedName
import com.radzhabov.moodtracker.data.network.api.response.responsebody.Condition

data class HourResponse(
    @SerializedName("time") val time: String? = null,
    @SerializedName("temp_c") val tempC: Float? = null,
    @SerializedName("condition") val condition: Condition? = null,
)