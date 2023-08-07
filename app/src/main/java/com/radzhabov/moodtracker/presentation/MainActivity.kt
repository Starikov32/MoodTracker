package com.radzhabov.moodtracker.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.jakewharton.threetenabp.AndroidThreeTen
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.presentation.navigation.AppNavigation
import com.radzhabov.moodtracker.presentation.theme.MoodTrackerTheme
import com.radzhabov.moodtracker.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
        ) {
            weatherViewModel.loadWeatherInfo()
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
        )

        setContent {
            MoodTrackerTheme {
                val navController = rememberNavController()
                val currentDate = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy; HH:mm")
                val formattedDate = currentDate.format(formatter)
                val painterDownIcon = painterResource(id = R.drawable.ic_down)
                val painterUpIcon = painterResource(id = R.drawable.ic_up)
                val padding = PaddingValues()
                val weatherState by weatherViewModel.state.collectAsState()

                AppNavigation(
                    navController = navController,
                    weatherState = weatherState,
                    date = formattedDate,
                    painterDownIcon = painterDownIcon,
                    painterUpIcon = painterUpIcon,
                    padding = padding,
                )

            }
        }
    }
}
