package com.radzhabov.moodtracker.data.repository

import com.radzhabov.moodtracker.data.mappers.toWeatherInfo
import com.radzhabov.moodtracker.data.network.api.WeatherApi
import com.radzhabov.moodtracker.domain.repository.WeatherApiRepository
import com.radzhabov.moodtracker.domain.util.Resource
import com.radzhabov.moodtracker.domain.weather.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherApiRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherApiRepository {

    override fun getWeatherData(lat: Double, long: Double): Flow<Resource<WeatherInfo>> = flow<Resource<WeatherInfo>> {

        try {
            val weatherData = api.getWeatherData(lat = lat, long = long).toWeatherInfo()
            emit(Resource.Success(data = weatherData))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(message = e.message ?: "Произошла неизвестная ошибка"))
        }
    }.flowOn(Dispatchers.IO)


}
