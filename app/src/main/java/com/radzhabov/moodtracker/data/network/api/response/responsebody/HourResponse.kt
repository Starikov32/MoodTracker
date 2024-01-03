package com.example.weatherapp.data.model.response.responsebody

import com.google.gson.annotations.SerializedName

data class HourResponse(
    @SerializedName("time") val time: String? = null,
    @SerializedName("temp_c") val tempC: Float? = null,
    @SerializedName("condition") val condition: Condition? = null,
)