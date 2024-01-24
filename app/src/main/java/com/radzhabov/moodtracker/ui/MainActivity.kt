package com.radzhabov.moodtracker.ui

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.screen.Screen
import com.radzhabov.moodtracker.ui.location.LocationManager
import com.radzhabov.moodtracker.ui.navigation.AppNavigation
import com.radzhabov.moodtracker.ui.theme.MoodTrackerTheme
import com.radzhabov.moodtracker.ui.viewmodel.MoodViewModel
import com.radzhabov.moodtracker.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<String>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val moodViewModel: MoodViewModel by viewModels()
    private lateinit var locationManager: LocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationManager = LocationManager(fusedLocationClient,this)

        checkPermission()

        setContent {
            MoodTrackerTheme {
                val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()
                val padding = PaddingValues()
                val weatherState by weatherViewModel.currentWeatherState.collectAsState()
                val context = LocalContext.current
                val screens: List<Screen> = listOf(
                    Screen(label = "Home", icon = painterResource(R.drawable.ic_home)),
                    Screen(label = "Stats", icon = painterResource(R.drawable.ic_stats)),
                    Screen(label = "Settings", icon = painterResource(R.drawable.ic_settings))
                )

                AppNavigation(
                    snackBarHostState,
                    screens,
                    navController,
                    weatherState,
                    moodViewModel,
                    padding,
                    context,
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        locationManager.checkLocation { handleLocationResult(it)}
    }

    private fun handleLocationResult(location: Location) {
        val locationString = "${location.latitude},${location.longitude}"
        weatherViewModel.loadWeatherInfo(locationString)
    }

    private fun checkPermission() {
        if (!locationManager.isLocationPermissionGranted()) {
            permissionListener()
            resultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun permissionListener() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            val message = if (isGranted) "Permission granted!" else "Permission denied!"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }


}
