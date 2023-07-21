package com.radzhabov.moodtracker.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radzhabov.moodtracker.domain.screen.Screens
import com.radzhabov.moodtracker.presentation.home.Home
import com.radzhabov.moodtracker.presentation.settings.Settings
import com.radzhabov.moodtracker.presentation.stats.Stats
import com.radzhabov.moodtracker.presentation.weather.Weather
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
        composable(route = Screens.Home.route){ Home(padding) }

        composable(route = Screens.Stats.route ){ Stats() }

        composable(route = Screens.Weather.route ){ Weather(weatherState) }

        composable(route = Screens.Settings.route ){ Settings() }

        composable(route = Screens.BottomNavBar.route ){ BottomNavBar(navController, weatherState) }

    }
}