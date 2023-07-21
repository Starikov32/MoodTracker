package com.radzhabov.moodtracker.domain.util

class Constants {
    companion object {
        const val URL_API = "https://api.open-meteo.com/"
        const val WEATHER_ENDPOINT = "v1/forecast?hourly=temperature_2m," +
                "weathercode,relativehumidity_2m,windspeed_10m,pressure_msl"
        const val latitude = "latitude"
        const val longitude = "longitude"
    }
}