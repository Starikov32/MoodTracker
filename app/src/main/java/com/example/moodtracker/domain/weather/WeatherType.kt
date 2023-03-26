package com.example.moodtracker.domain.weather

import androidx.annotation.DrawableRes
import com.example.moodtracker.R

sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes val iconRes: Int,
) {
    object Sunny : WeatherType(
        weatherDescription = "Sunny",
        iconRes = R.mipmap.ic_sunny,
    )
    object Cloudy : WeatherType(
        weatherDescription = "Cloudy",
        iconRes = R.mipmap.ic_cloudy,
    )
    object Foggy : WeatherType(
        weatherDescription = "Foggy",
        iconRes = R.mipmap.ic_foggy,
    )
    object Hail : WeatherType(
        weatherDescription = "Hail",
        iconRes = R.mipmap.ic_hail,
    )
    object HeavyRain : WeatherType(
        weatherDescription = "Heavy Rain",
        iconRes = R.mipmap.ic_heavy_rain,
    )
    object Rainy : WeatherType(
        weatherDescription = "Rainy",
        iconRes = R.mipmap.ic_rainy,
    )
    object Wetly : WeatherType(
        weatherDescription = "Wetly",
        iconRes = R.mipmap.ic_wetly,
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
