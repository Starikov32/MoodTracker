package com.radzhabov.moodtracker.home.data.network.api.response.responsebody

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text") val text: String? = null,
    @SerializedName("icon") val icon: String? = null,
)
