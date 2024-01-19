package com.radzhabov.moodtracker.data.repositories

import com.radzhabov.moodtracker.data.mapper.toCurrentCardWeather
import com.radzhabov.moodtracker.data.network.api.WeatherApi
import com.radzhabov.moodtracker.data.network.api.response.WeatherResponse
import com.radzhabov.moodtracker.data.network.service.NetworkService.Companion.handleCall
import com.radzhabov.moodtracker.domain.repositories.WeatherApiRepository
import com.radzhabov.moodtracker.domain.util.Constants.Companion.ALERTS
import com.radzhabov.moodtracker.domain.util.Constants.Companion.API_KEY
import com.radzhabov.moodtracker.domain.util.Constants.Companion.AQI
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherApiRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
): WeatherApiRepository {
    override fun getWeatherData(city: String) = flow {
        val call = weatherApi.getWeatherForecast(API_KEY, city, 1, AQI, ALERTS)
        val response: WeatherResponse? = handleCall(call)
        val currentWeatherCardModel = response?.toCurrentCardWeather()
        currentWeatherCardModel?.let { emit(it) }
        }.flowOn(IO)

}
