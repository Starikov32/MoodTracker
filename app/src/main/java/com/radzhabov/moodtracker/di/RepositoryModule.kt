package com.radzhabov.moodtracker.di

import com.radzhabov.moodtracker.data.repository.WeatherRepositoryImpl
import com.radzhabov.moodtracker.domain.repository.WeatherRepository
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
        weatherRepositoryImpl: WeatherRepositoryImpl,
    ): WeatherRepository
}
