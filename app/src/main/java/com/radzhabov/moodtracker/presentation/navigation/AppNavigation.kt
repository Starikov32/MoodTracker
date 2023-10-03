package com.radzhabov.moodtracker.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radzhabov.moodtracker.domain.screen.Screens
import com.radzhabov.moodtracker.presentation.home.content.HomeScreen
import com.radzhabov.moodtracker.presentation.settings.SettingsScreen
import com.radzhabov.moodtracker.presentation.stats.StatsScreen
import com.radzhabov.moodtracker.domain.weather.WeatherState
import com.radzhabov.moodtracker.presentation.home.content.EditHomeContentScreen

@Composable
fun AppNavigation(
    navController: NavController,
    weatherState: WeatherState,
    date: String,
    painterDownIcon: Painter,
    painterUpIcon: Painter,
    padding: PaddingValues,
    context: Context,
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.BottomNavBar.route
    ){

        composable(route = Screens.Home.route){
            HomeScreen(navController, weatherState, date, painterDownIcon, painterUpIcon, context)
        }

        composable(route = Screens.Stats.route ){ StatsScreen() }

        composable(route = Screens.Settings.route ){ SettingsScreen(padding) }

        composable(route = Screens.BottomNavBar.route ){
            BottomNavBar(navController, weatherState, date, painterDownIcon, painterUpIcon, context)
        }

        composable(route = Screens.EditHomeContentScreen.route) {
            EditHomeContentScreen(navController, painterDownIcon, painterUpIcon, context)
        }

    }
}