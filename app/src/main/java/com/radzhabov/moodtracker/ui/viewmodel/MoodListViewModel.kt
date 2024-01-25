package com.radzhabov.moodtracker.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.data.db.entities.Mood
import com.radzhabov.moodtracker.domain.repositories.MoodRepository
import com.radzhabov.moodtracker.domain.util.Routes
import com.radzhabov.moodtracker.domain.util.UiEvent
import com.radzhabov.moodtracker.ui.home.MoodListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoodListViewModel @Inject constructor(
    private val moodRepository: MoodRepository,
    @ApplicationContext private val context: Context
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
                    viewModelScope.launch(Dispatchers.IO) {
                        moodRepository.insertMood(mood)
                    }
                }
            }
            is MoodListEvent.OnDeleteMoodClick -> {
                viewModelScope.launch(Dispatchers.Main) {
                    deletedMood = event.mood
                    moodRepository.deleteMood(event.mood)
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = context.getString(R.string.factor_mood_deleted),
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