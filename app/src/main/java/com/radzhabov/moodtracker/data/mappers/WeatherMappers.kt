package com.radzhabov.moodtracker.data.mappers

import com.radzhabov.moodtracker.data.network.dto.WeatherDataDto
import com.radzhabov.moodtracker.data.network.dto.WeatherDto
import com.radzhabov.moodtracker.domain.weather.WeatherData
import com.radzhabov.moodtracker.domain.weather.WeatherInfo
import com.radzhabov.moodtracker.domain.weather.WeatherType
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData,
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    val HOUR = 24

    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode),
            ),
        )
    }.groupBy {
        it.index / HOUR
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData,
    )
}