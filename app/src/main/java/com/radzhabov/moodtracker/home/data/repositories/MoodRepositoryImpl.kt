package com.radzhabov.moodtracker.home.data.repositories

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.radzhabov.moodtracker.main.data.dao.MoodDao
import com.radzhabov.moodtracker.main.data.entities.Mood
import com.radzhabov.moodtracker.home.domain.repositories.MoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoodRepositoryImpl @Inject constructor(
    private val moodDao: MoodDao,
): MoodRepository {

    override suspend fun insertMood(mood: Mood) {
        try {
            moodDao.insertMood(mood)
        } catch (e: SQLiteConstraintException) {
            val message = "SQLite constraint error: ${e.localizedMessage}"
            Log.e(LOG_TAG, message)
        }
    }

    override suspend fun deleteMood(mood: Mood) {
        try {
            moodDao.deleteMood(mood)
        } catch (e: SQLiteConstraintException) {
            val message = "SQLite constraint error: ${e.localizedMessage}"
            Log.e(LOG_TAG, message)
        }
    }

    override suspend fun getMoodById(id: Int) = moodDao.getMoodById(id)

    override fun getMoods(): Flow<List<Mood>> = moodDao.getMoods()

    companion object {
        const val LOG_TAG = "MoodRepository"
    }
}
