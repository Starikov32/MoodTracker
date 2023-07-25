package com.radzhabov.moodtracker.di

import com.radzhabov.moodtracker.data.repository.WeatherApiRepositoryImpl
import com.radzhabov.moodtracker.domain.repository.WeatherApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepositoryWeather(
        weatherApiRepositoryImpl: WeatherApiRepositoryImpl,
    ): WeatherApiRepository
}
