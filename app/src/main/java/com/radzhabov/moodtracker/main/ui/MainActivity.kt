package com.radzhabov.moodtracker.main.ui

import android.Manifest
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.home.domain.util.Routes
import com.radzhabov.moodtracker.authorization.ui.viewmodel.AuthorizationViewModel
import com.radzhabov.moodtracker.main.ui.location.LocationManager
import com.radzhabov.moodtracker.main.ui.navigation.appNavigation
import com.radzhabov.moodtracker.main.ui.theme.moodTrackerTheme
import com.radzhabov.moodtracker.main.ui.utils.showToast
import com.radzhabov.moodtracker.home.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<String>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val authViewModel: AuthorizationViewModel by viewModels()
    private lateinit var locationManager: LocationManager
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationManager = LocationManager(fusedLocationClient,this)
        checkPermission()

        setContent {
            moodTrackerTheme {
                val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
                navController = rememberNavController()
                val modifier = Modifier
                val weatherState by weatherViewModel.currentWeatherState.collectAsState()

                appNavigation(
                    modifier,
                    snackBarHostState,
                    navController,
                    weatherState,
                )

                checkAndNavigate()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        locationManager.checkLocation { handleLocationResult(it)}
    }

    private fun checkAndNavigate() {
        if (authViewModel.isLoggedIn) {
            navController.navigate(Routes.BOTTOM) {
                popUpTo(Routes.LOGIN) {
                    inclusive = true
                }
            }
        }
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
            val message = if (isGranted) R.string.permission_granted else R.string.permission_denied
            this.showToast(message)
        }
    }


}
