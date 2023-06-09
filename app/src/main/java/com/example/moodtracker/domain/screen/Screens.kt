package com.example.moodtracker.domain.screen

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object Stats: Screens("stats")
    object Weather: Screens("weather")
    object Settings: Screens("settings")
    object BottomNavBar: Screens("bottom")
}