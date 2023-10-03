package com.radzhabov.moodtracker.domain.screen

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object Stats: Screens("stats")
    object EditHomeContentScreen: Screens("edit_home_content_screen")
    object Settings: Screens("settings")
    object BottomNavBar: Screens("bottom")
}