package com.radzhabov.moodtracker.home.ui

import com.radzhabov.moodtracker.main.data.entities.Mood

sealed class MoodListEvent {
    object OnAddMoodClick : MoodListEvent()
    data class OnDeleteMoodClick(val mood: Mood): MoodListEvent()
    data class OnMoodClick(val mood: Mood): MoodListEvent()
    object OnUndoDeleteClick: MoodListEvent()

}
