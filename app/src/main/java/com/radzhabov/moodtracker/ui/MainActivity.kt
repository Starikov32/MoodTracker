package com.radzhabov.moodtracker.ui

import android.Manifest
import android.content.Context
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.radzhabov.moodtracker.data.user.UserPreferencesManager
import com.radzhabov.moodtracker.ui.authorization.Register
import com.radzhabov.moodtracker.ui.location.LocationManager
import com.radzhabov.moodtracker.ui.navigation.AppNavigation
import com.radzhabov.moodtracker.ui.theme.MoodTrackerTheme
import com.radzhabov.moodtracker.ui.viewmodel.MoodEditViewModel
import com.radzhabov.moodtracker.ui.viewmodel.MoodListViewModel
import com.radzhabov.moodtracker.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<String>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var locationManager: LocationManager
    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContext = this.applicationContext
        val dataStore: DataStore<Preferences> = appContext._dataStore

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationManager = LocationManager(fusedLocationClient,this)

        checkPermission()

        setContent {
            MoodTrackerTheme {
                val userPreferencesManager = UserPreferencesManager(dataStore)
                val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()
                val padding = PaddingValues()
                val modifier = Modifier
                val weatherState by weatherViewModel.currentWeatherState.collectAsState()
                val context = LocalContext.current

//                AppNavigation(
//                    modifier,
//                    snackBarHostState,
//                    navController,
//                    weatherState,
//                    padding
//                )

                Register(
                    context = context,
                    userPreferencesManager = userPreferencesManager,
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
