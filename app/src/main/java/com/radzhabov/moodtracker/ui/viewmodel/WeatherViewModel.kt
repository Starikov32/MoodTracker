package com.radzhabov.moodtracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.domain.repository.WeatherApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherApiRepository,
) : ViewModel() {

    private val _currentWeatherState = MutableStateFlow<CurrentWeatherCardModel?>(null)
    val currentWeatherState: StateFlow<CurrentWeatherCardModel?>
        get() = _currentWeatherState

    fun loadWeatherInfo(city: String) {
        viewModelScope.launch {
            weatherRepository.getWeatherData(city).collect { currentWeather ->
                _currentWeatherState.value = currentWeather
            }
        }
    }


}
