package com.radzhabov.moodtracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.data.db.entities.Mood
import com.radzhabov.moodtracker.domain.repositories.MoodRepository
import com.radzhabov.moodtracker.domain.util.Routes
import com.radzhabov.moodtracker.domain.util.UiEvent
import com.radzhabov.moodtracker.ui.home.MoodListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoodListViewModel @Inject constructor(
    private val moodRepository: MoodRepository
): ViewModel() {
    val mood = moodRepository.getMoods()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedMood: Mood? = null

    fun onEvent(event: MoodListEvent) {
        when(event) {
            is MoodListEvent.OnMoodClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.MOOD_EDIT + "?moodId=${event.mood.id}"))
            }
            is MoodListEvent.OnAddMoodClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.MOOD_EDIT))
            }
            is MoodListEvent.OnUndoDeleteClick -> {
                deletedMood?.let { mood ->
                    viewModelScope.launch {
                        moodRepository.insertMood(mood)
                    }
                }
            }
            is MoodListEvent.OnDeleteMoodClick -> {
                viewModelScope.launch {
                    deletedMood = event.mood
                    moodRepository.deleteMood(event.mood)
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "Mood deleted",
                        action = "Undo"
                    ))
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}