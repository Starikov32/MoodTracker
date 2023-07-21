package com.radzhabov.moodtracker.presentation

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object Stats: Screens("stats")
    object Weather: Screens("weather")
    object Settings: Screens("settings")
    object BottomNavBar: Screens("bottom")
}