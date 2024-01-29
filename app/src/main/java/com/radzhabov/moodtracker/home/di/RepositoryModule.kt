package com.radzhabov.moodtracker.home.di

import com.radzhabov.moodtracker.home.data.repositories.WeatherApiRepositoryImpl
import com.radzhabov.moodtracker.home.domain.repositories.WeatherApiRepository
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
