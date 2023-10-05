package com.radzhabov.moodtracker.presentation.navigation

import android.content.Context
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.radzhabov.moodtracker.domain.screen.Screen
import com.radzhabov.moodtracker.presentation.home.content.HomeScreen
import com.radzhabov.moodtracker.presentation.settings.SettingsScreen
import com.radzhabov.moodtracker.presentation.stats.StatsScreen
import com.radzhabov.moodtracker.domain.weather.WeatherState

@Composable
fun BottomNavBar(
    snackBarHostState: SnackbarHostState,
    screens: List<Screen>,
    navController: NavController,
    weatherState: WeatherState,
    date: String,
    context: Context,
    isExpanded: Boolean,
) {
    var selectedScreen by remember { mutableIntStateOf(0) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            NavigationBarContent(
                screens = screens,
                selectedScreen = selectedScreen,
                onScreenSelected = { newIndex ->
                    selectedScreen = newIndex
                }
            )
        }
    ) { innerPadding ->
        when (selectedScreen) {
            0 -> HomeScreen(navController, weatherState, date, context, isExpanded)
            1 -> StatsScreen()
            2 -> SettingsScreen(innerPadding)
        }
    }
}
