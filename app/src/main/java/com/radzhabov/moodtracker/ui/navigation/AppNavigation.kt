package com.radzhabov.moodtracker.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.domain.util.Routes
import com.radzhabov.moodtracker.ui.home.HomeScreen
import com.radzhabov.moodtracker.ui.settings.SettingsScreen
import com.radzhabov.moodtracker.ui.stats.StatsScreen
import com.radzhabov.moodtracker.ui.home.edit.MoodEdit
import com.radzhabov.moodtracker.ui.viewmodel.MoodEditViewModel
import com.radzhabov.moodtracker.ui.viewmodel.MoodListViewModel

@Composable
fun AppNavigation(
    modifier: Modifier,
    snackBarHostState: SnackbarHostState,
    navController: NavController,
    weatherState: CurrentWeatherCardModel?,
    moodListViewModel: MoodListViewModel,
    moodEditViewModel: MoodEditViewModel,
    padding: PaddingValues,
){
    var selectedScreen by remember { mutableIntStateOf(0) }

    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.BOTTOM
    ){

        composable(route = Routes.HOME){
            HomeScreen(
                modifier = modifier,
                onNavigate = { navController.navigate(it.route) },
                viewModel = moodListViewModel,
                weatherState = weatherState,

            )
        }

        composable(route = Routes.STATS ){ StatsScreen() }

        composable(route = Routes.SETTINGS ){ SettingsScreen(padding) }

        composable(route = Routes.BOTTOM ){
            BottomNavBar(
                snackBarHostState,
                moodListViewModel,
                navController,
                selectedScreen,
                {
                    selectedScreen = it
                },
                weatherState
            )
        }

        composable(
            route = Routes.MOOD_EDIT + "?moodId={moodId}",
            arguments = listOf(
                navArgument(name = "moodId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            MoodEdit(
                onPopBackStack = { navController.popBackStack() },
                moodEditViewModel
            )
        }

    }
}