package com.radzhabov.moodtracker.data.repository

import com.radzhabov.moodtracker.data.db.dao.WeatherDao
import com.radzhabov.moodtracker.data.mappers.mapToWeather
import com.radzhabov.moodtracker.data.mappers.mapToWeatherEntity
import com.radzhabov.moodtracker.data.model.Weather
import com.radzhabov.moodtracker.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeatherFromDb(time: LocalDateTime): Weather? = withContext(IO) {
        val weatherEntity = weatherDao.getWeather(time)
        return@withContext weatherEntity?.mapToWeather()
    }

    override suspend fun insertWeatherIntoDb(weather: Weather) = withContext(IO) {
        weatherDao.addWeather(weather.mapToWeatherEntity())
    }
}