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
import com.radzhabov.moodtracker.presentation.weather.WeatherScreen
import com.radzhabov.moodtracker.presentation.weather.WeatherState

@Composable
fun AppNavigation(
    navController: NavController,
    weatherState: WeatherState
){
    val padding = PaddingValues()

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.BottomNavBar.route
    ){
        composable(route = Screens.Home.route){ HomeScreen(weatherState) }

        composable(route = Screens.Stats.route ){ StatsScreen() }

        composable(route = Screens.Weather.route ){ WeatherScreen( padding) }

        composable(route = Screens.Settings.route ){ SettingsScreen() }

        composable(route = Screens.BottomNavBar.route ){ BottomNavBar(navController, weatherState) }

    }
}