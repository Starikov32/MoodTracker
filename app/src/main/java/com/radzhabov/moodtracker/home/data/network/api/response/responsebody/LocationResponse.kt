package com.radzhabov.moodtracker.home.data.network.api.response.responsebody

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("localtime") val localtime: String? = null,
)
