package com.radzhabov.moodtracker.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
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
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            requestLocation()
        } else {
            Toast.makeText(
                applicationContext,
                "Enable Permissions",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
        setupUserInterface()
    }

    private fun setupUserInterface() {
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

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.requestLocationUpdates(
            LocationRequest.create(),
            object : LocationCallback() {
                fun onLocationResult() {
                    requestLocation()
                }
            },
            Looper.getMainLooper())
    }

    private fun requestLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val latitude = location.latitude
                        val longitude = location.longitude

                        weatherViewModel.loadWeatherInfo(latitude, longitude)
                    }
                }.addOnFailureListener {
                    it.printStackTrace()
                }.addOnCompleteListener {
                    if (it.result == null) {
                        requestLocation()
                    }
                }
        }
    }
}
