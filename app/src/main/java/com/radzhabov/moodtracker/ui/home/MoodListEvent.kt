package com.radzhabov.moodtracker.ui.home

import com.radzhabov.moodtracker.data.db.entities.Mood

sealed class MoodListEvent {
    object OnAddMoodClick : MoodListEvent()
    data class OnDeleteMoodClick(val mood: Mood): MoodListEvent()
    data class OnMoodClick(val mood: Mood): MoodListEvent()
    object OnUndoDeleteClick: MoodListEvent()

}
