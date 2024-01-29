package com.radzhabov.moodtracker.home.data.repositories

import com.radzhabov.moodtracker.home.data.mapper.toCurrentCardWeather
import com.radzhabov.moodtracker.home.data.network.api.WeatherApi
import com.radzhabov.moodtracker.home.data.network.api.response.WeatherResponse
import com.radzhabov.moodtracker.home.data.network.service.NetworkService.Companion.handleCall
import com.radzhabov.moodtracker.home.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.home.domain.repositories.WeatherApiRepository
import com.radzhabov.moodtracker.home.domain.util.Constants.ALERTS
import com.radzhabov.moodtracker.home.domain.util.Constants.API_KEY
import com.radzhabov.moodtracker.home.domain.util.Constants.AQI
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherApiRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
): WeatherApiRepository {
    override fun getWeatherData(city: String): Flow<CurrentWeatherCardModel> = flow {
            val call = weatherApi.getWeatherForecast(API_KEY, city, 1, AQI, ALERTS)
            val response: WeatherResponse? = handleCall(call)
            val currentWeatherCardModel = response?.toCurrentCardWeather()
            currentWeatherCardModel?.let { emit(it) }
        }.flowOn(IO)

}
