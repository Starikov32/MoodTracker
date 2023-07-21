package com.radzhabov.moodtracker.di

import android.content.Context
import com.radzhabov.moodtracker.data.db.AppDatabase
import com.radzhabov.moodtracker.data.db.dao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
    AppDatabase.getInstance(appContext)

    @Provides
    @Singleton
    fun provideWeatherDao(appDatabase: AppDatabase): WeatherDao =
        appDatabase.weatherDao()

}