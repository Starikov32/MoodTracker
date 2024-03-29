package com.radzhabov.moodtracker.main.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.radzhabov.moodtracker.main.data.dao.MoodDao
import com.radzhabov.moodtracker.main.data.entities.Mood

@Database(
    version = 1,
    exportSchema = false,
    entities = [Mood::class]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun moodDao(): MoodDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).fallbackToDestructiveMigration().build()
        }

    }
}
