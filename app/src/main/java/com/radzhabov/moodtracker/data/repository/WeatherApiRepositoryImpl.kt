package com.radzhabov.moodtracker.data.repository

import com.radzhabov.moodtracker.data.mappers.toWeatherInfo
import com.radzhabov.moodtracker.data.network.api.WeatherApi
import com.radzhabov.moodtracker.domain.repository.WeatherApiRepository
import com.radzhabov.moodtracker.domain.util.Resource
import com.radzhabov.moodtracker.domain.weather.WeatherInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherApiRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherApiRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long,
                ).toWeatherInfo(),
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}
