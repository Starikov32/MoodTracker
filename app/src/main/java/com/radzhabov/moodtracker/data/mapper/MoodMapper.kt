package com.radzhabov.moodtracker.data.mapper

import com.radzhabov.moodtracker.data.db.entities.MoodEntity
import com.radzhabov.moodtracker.data.dto.Mood

fun MoodEntity.mapMood() = Mood(
    name = this.name,
    state = this.state
)

fun Mood.mapMoodEntity() = MoodEntity(
    name = this.name,
    state = this.state
)