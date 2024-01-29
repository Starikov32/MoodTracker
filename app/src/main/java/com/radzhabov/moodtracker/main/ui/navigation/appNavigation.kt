package com.radzhabov.moodtracker.main.ui.navigation

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.home.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.home.domain.util.Routes
import com.radzhabov.moodtracker.authorization.ui.login.login
import com.radzhabov.moodtracker.authorization.ui.registration.registration
import com.radzhabov.moodtracker.home.ui.homeScreen
import com.radzhabov.moodtracker.settings.ui.settingsScreen
import com.radzhabov.moodtracker.stats.ui.statsScreen
import com.radzhabov.moodtracker.home.ui.edit.moodEdit
import com.radzhabov.moodtracker.main.ui.utils.showToast

@Composable
fun appNavigation(
    modifier: Modifier,
    snackBarHostState: SnackbarHostState,
    navController: NavController,
    weatherState: CurrentWeatherCardModel?,
) {
    val context = LocalContext.current
    val padding = PaddingValues()
    var selectedScreen by remember { mutableIntStateOf(0) }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    backDispatcher?.let {
        backHandler(it) {
            if (navController.currentBackStackEntry?.destination?.route == Routes.BOTTOM) {
                context.showToast(R.string.correct_screen)
            } else {
                navController.popBackStack()
            }
        }
    }

    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.LOGIN
    ) {

        composable(route = Routes.REGISTRATION) { registration(context, navController) }

        composable(route = Routes.LOGIN) { login(context, navController) }

        composable(route = Routes.HOME) {
            homeScreen(
                modifier = modifier,
                onNavigate = { navController.navigate(it.route) },
                weatherState = weatherState,
            )
        }

        composable(route = Routes.STATS ) { statsScreen() }

        composable(route = Routes.SETTINGS ) { settingsScreen(padding) }

        composable(route = Routes.BOTTOM ) {
            bottomNavBar(
                snackBarHostState,
                navController,
                selectedScreen,
                { selectedScreen = it },
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
            moodEdit(
                onPopBackStack = { navController.popBackStack() },
            )
        }

    }
}

@Composable
fun backHandler(
    dispatcher: OnBackPressedDispatcher,
    onBackEvent: () -> Unit
) {
    DisposableEffect(key1 = dispatcher) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackEvent()
            }
        }
        dispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}
