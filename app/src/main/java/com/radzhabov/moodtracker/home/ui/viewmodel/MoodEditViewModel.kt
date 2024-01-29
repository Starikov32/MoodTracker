package com.radzhabov.moodtracker.home.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.main.data.db.entities.Mood
import com.radzhabov.moodtracker.home.domain.repositories.MoodRepository
import com.radzhabov.moodtracker.home.domain.util.UiEvent
import com.radzhabov.moodtracker.home.ui.edit.MoodEditEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoodEditViewModel @Inject constructor(
    private val moodRepository: MoodRepository,
    savedStateHandle: SavedStateHandle
) :ViewModel() {
    var mood by mutableStateOf<Mood?>(null)
        private set

    var name by mutableStateOf("")
        private set

    var stateNumber by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val moodId = savedStateHandle.get<Int>("moodId") ?: -1
        if (moodId!= -1) {
            viewModelScope.launch {
                moodRepository.getMoodById(moodId)?.let { mood ->
                    name = mood.name
                    stateNumber = mood.stateNumber
                    this@MoodEditViewModel.mood = mood
                }
            }
        }
    }

    fun onEvent(event: MoodEditEvent) {
        when(event) {
            is MoodEditEvent.OnNameChange -> {
                name = event.name
            }
            is MoodEditEvent.OnStateNumberChange -> {
                stateNumber = event.stateNumber
            }
            is MoodEditEvent.OnSaveMoodClick -> {
                viewModelScope.launch {
                    if (name.isBlank() || stateNumber.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(
                            message = "The name and state number can't be empty"
                        ))
                    } else {
                        moodRepository.insertMood(
                            Mood(
                                name = name,
                                stateNumber = stateNumber,
                                id = mood?.id
                            )
                        )
                        sendUiEvent(UiEvent.PopBackStack)
                    }
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
