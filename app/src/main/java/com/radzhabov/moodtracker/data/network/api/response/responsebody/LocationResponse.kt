package com.example.weatherapp.data.model.response.responsebody

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("localtime") val localtime: String? = null,
)
