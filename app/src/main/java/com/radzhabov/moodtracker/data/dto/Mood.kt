package com.radzhabov.moodtracker.data.dto

data class Mood(
    var name: String,
    var state: Int,
) {
    companion object {
        fun defaultMood() = Mood(name = "", state = 0)
    }
}
