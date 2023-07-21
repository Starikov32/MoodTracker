package com.example.moodtracker.domain.weather

import androidx.annotation.DrawableRes
import com.example.moodtracker.R

sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes val iconRes: Int,
) {
    object Sunny : WeatherType(
        weatherDescription = "Sunny",
        iconRes = R.drawable.ic_sunny,
    )
    object Cloudy : WeatherType(
        weatherDescription = "Cloudy",
        iconRes = R.drawable.ic_cloudy,
    )
    object Foggy : WeatherType(
        weatherDescription = "Foggy",
        iconRes = R.drawable.ic_fog,
    )
    object Hail : WeatherType(
        weatherDescription = "Hail",
        iconRes = R.drawable.ic_hail,
    )
    object HeavyRain : WeatherType(
        weatherDescription = "Heavy Rain",
        iconRes = R.drawable.ic_heavy_rain,
    )
    object Rainy : WeatherType(
        weatherDescription = "Rainy",
        iconRes = R.drawable.ic_rainy,
    )
    object Wetly : WeatherType(
        weatherDescription = "Wetly",
        iconRes = R.drawable.ic_wetly,
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
