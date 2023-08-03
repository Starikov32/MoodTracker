package com.radzhabov.moodtracker.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radzhabov.moodtracker.domain.screen.Screens
import com.radzhabov.moodtracker.presentation.home.HomeScreen
import com.radzhabov.moodtracker.presentation.settings.SettingsScreen
import com.radzhabov.moodtracker.presentation.stats.StatsScreen
import com.radzhabov.moodtracker.domain.weather.WeatherState

@Composable
fun AppNavigation(
    navController: NavController,
    weatherState: WeatherState,
    date: String,
){
    val padding = PaddingValues()

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.BottomNavBar.route
    ){
        composable(route = Screens.Home.route){ HomeScreen(weatherState, date) }

        composable(route = Screens.Stats.route ){ StatsScreen() }

        composable(route = Screens.Settings.route ){ SettingsScreen(padding) }

        composable(route = Screens.BottomNavBar.route ){ BottomNavBar(navController, weatherState, date) }

    }
}