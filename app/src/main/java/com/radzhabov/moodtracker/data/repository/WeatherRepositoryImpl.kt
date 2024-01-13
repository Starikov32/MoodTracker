package com.radzhabov.moodtracker.data.repository

import com.radzhabov.moodtracker.data.db.dao.MoodDao
import com.radzhabov.moodtracker.data.dto.Mood
import com.radzhabov.moodtracker.data.mapper.mapMood
import com.radzhabov.moodtracker.data.mapper.mapMoodEntity
import com.radzhabov.moodtracker.data.mapper.toCurrentCardWeather
import com.radzhabov.moodtracker.data.network.api.WeatherApi
import com.radzhabov.moodtracker.data.network.api.response.WeatherResponse
import com.radzhabov.moodtracker.data.network.service.NetworkService.Companion.handleCall
import com.radzhabov.moodtracker.domain.repository.WeatherRepository
import com.radzhabov.moodtracker.domain.util.Constants.Companion.ALERTS
import com.radzhabov.moodtracker.domain.util.Constants.Companion.API_KEY
import com.radzhabov.moodtracker.domain.util.Constants.Companion.AQI
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val moodDao: MoodDao,
) : WeatherRepository {
    override fun getWeatherData(city: String) = flow {
        val call = weatherApi.getWeatherForecast(API_KEY, city, 1, AQI, ALERTS)
        val response: WeatherResponse? = handleCall(call)
        val currentWeatherCardModel = response?.toCurrentCardWeather()
        currentWeatherCardModel?.let { emit(it) }
        }.flowOn(IO)

    override suspend fun getMood(name: String) = withContext(IO) {
        val moodEntity = moodDao.getMood(name)
        return@withContext moodEntity?.mapMood()
    }

    override suspend fun insertMoodCriteria(mood: Mood) = withContext(IO) {
        return@withContext moodDao.insertMoodCriteria(mood.mapMoodEntity())
    }

}
