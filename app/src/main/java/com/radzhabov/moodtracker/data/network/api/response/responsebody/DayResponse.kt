package com.example.weatherapp.data.model.response.responsebody

import com.google.gson.annotations.SerializedName

data class DayResponse(
    @SerializedName("maxtemp_c") val maxTempC: Float? = null,
    @SerializedName("mintemp_c") val minTempC: Float? = null,
    @SerializedName("condition") val condition: Condition? = null,
)