package com.radzhabov.moodtracker.data.repositories

import android.util.Log
import com.radzhabov.moodtracker.data.db.dao.MoodDao
import com.radzhabov.moodtracker.data.dto.Mood
import com.radzhabov.moodtracker.data.mapper.mapMood
import com.radzhabov.moodtracker.data.mapper.mapMoodEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoodRepository @Inject constructor(
    private val moodDao: MoodDao,
) {

    suspend fun getMood(name: String) = withContext(IO) {
        return@withContext try {
            val moodEntity = moodDao.getMood(name)
            moodEntity?.mapMood()
        } catch (e: Exception) {
            val message = "Error executing repository: ${e.localizedMessage}"
            Log.e(LOG_TAG, message)
        }
    }

    suspend fun insertMoodCriteria(mood: Mood) = withContext(IO) {
        return@withContext try {
            moodDao.insertMoodCriteria(mood.mapMoodEntity())
        } catch (e: Exception) {
            throw IllegalStateException(EXCEPTION)
        }
    }

    companion object {
        const val EXCEPTION = "Repository returns nothing"
        const val LOG_TAG = "MoodRepository"
    }
}
