package com.radzhabov.moodtracker.ui.home.edit

sealed class MoodEditEvent {
    data class OnNameChange(val name: String) : MoodEditEvent()
    data class OnStateNumberChange(val stateNumber: String) : MoodEditEvent()
    object OnSaveMoodClick: MoodEditEvent()
}
