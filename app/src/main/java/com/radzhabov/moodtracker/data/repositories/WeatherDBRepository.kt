package com.radzhabov.moodtracker.data.repositories

import com.radzhabov.moodtracker.data.db.dao.MoodDao
import com.radzhabov.moodtracker.data.dto.Mood
import com.radzhabov.moodtracker.data.mapper.mapMood
import com.radzhabov.moodtracker.data.mapper.mapMoodEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDBRepository @Inject constructor(
    private val moodDao: MoodDao,
) {

    suspend fun getMood(name: String) = withContext(IO) {
        val moodEntity = moodDao.getMood(name)
        return@withContext moodEntity?.mapMood()
    }

    suspend fun insertMoodCriteria(mood: Mood) = withContext(IO) {
        return@withContext moodDao.insertMoodCriteria(mood.mapMoodEntity())
    }

}
