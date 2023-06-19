package com.example.moodtracker.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.Modifier
import com.example.moodtracker.presentation.ui.theme.DarkBlue
import com.example.moodtracker.presentation.ui.theme.DeepBlue
import com.example.moodtracker.presentation.ui.theme.MoodTrackerTheme
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
        ) {
            viewModel.loadWeatherInfo()
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
        )

        setContent {
            MoodTrackerTheme {
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue),
                ) {
                    items(count = 4) {
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = DeepBlue,
                        )
                    }

                }
            }
        }
    }
}
