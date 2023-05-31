package com.example.moodtracker.data.repository

import com.example.moodtracker.data.mappers.toWeatherInfo
import com.example.moodtracker.data.network.api.WeatherApi
import com.example.moodtracker.domain.repository.WeatherRepository
import com.example.moodtracker.domain.util.Resource
import com.example.moodtracker.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {

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
