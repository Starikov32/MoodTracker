package com.radzhabov.moodtracker.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.home.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.home.domain.repositories.WeatherApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherApiRepository: WeatherApiRepository,
) : ViewModel() {

    private val _currentWeatherState = MutableStateFlow<CurrentWeatherCardModel?>(null)
    val currentWeatherState: StateFlow<CurrentWeatherCardModel?>
        get() = _currentWeatherState

    fun loadWeatherInfo(city: String) {
        viewModelScope.launch {
            weatherApiRepository.getWeatherData(city).collect { currentWeather ->
                _currentWeatherState.value = currentWeather
            }
        }
    }

}
