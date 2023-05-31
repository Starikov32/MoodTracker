package com.example.moodtracker.domain.weather

import androidx.annotation.DrawableRes
import com.example.moodtracker.R

sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes val iconRes: Int,
) {
    object Sunny : WeatherType(
        weatherDescription = "Sunny",
        iconRes = R.mipmap.ic_sunny_foreground,
    )
    object Cloudy : WeatherType(
        weatherDescription = "Cloudy",
        iconRes = R.mipmap.ic_cloudy_foreground,
    )
    object Foggy : WeatherType(
        weatherDescription = "Foggy",
        iconRes = R.mipmap.ic_foggy_foreground,
    )
    object Hail : WeatherType(
        weatherDescription = "Hail",
        iconRes = R.mipmap.ic_hail_foreground,
    )
    object HeavyRain : WeatherType(
        weatherDescription = "Heavy Rain",
        iconRes = R.mipmap.ic_heavy_rain_foreground,
    )
    object Rainy : WeatherType(
        weatherDescription = "Rainy",
        iconRes = R.mipmap.ic_rainy_foreground,
    )
    object Wetly : WeatherType(
        weatherDescription = "Wetly",
        iconRes = R.mipmap.ic_wetly_foreground,
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> Sunny
                3 -> Cloudy
                45 -> Foggy
                48 -> Wetly
                61 -> Rainy
                65 -> HeavyRain
                96 -> Hail
                else -> Sunny
            }
        }
    }
}
