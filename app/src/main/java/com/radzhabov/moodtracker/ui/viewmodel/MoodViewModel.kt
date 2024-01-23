package com.radzhabov.moodtracker.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.data.dto.Mood
import com.radzhabov.moodtracker.data.repositories.MoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoodViewModel @Inject constructor(
    private val moodRepository: MoodRepository
) :ViewModel() {
    private val _currentMoodState = MutableStateFlow(Mood.defaultMood())
    private val currentMood: StateFlow<Mood> = _currentMoodState.asStateFlow()


    fun insertMood(name: String, state: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            currentMood.value.let {
                moodRepository.insertMoodCriteria(Mood(name, state))
            }
        } catch (e: Exception) {
            val message = "Error executing ViewModel: ${e.localizedMessage}"
            Log.e(LOG_TAG, message)
        }
    }

    fun getMood(name: String) = flow {
        try {
            val mood = withContext(Dispatchers.IO) {
                moodRepository.getMood(name)
            }
            emit(mood !== null)
        } catch (e: Exception) {
            val message = "Error executing ViewModel: ${e.localizedMessage}"
            Log.e(LOG_TAG, message)
            emit(false)
        }
    }

    companion object {
        const val LOG_TAG = "MoodViewModel"
    }
}