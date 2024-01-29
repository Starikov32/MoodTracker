package com.radzhabov.moodtracker.main.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class LocationManager(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val activity: ComponentActivity,
) {

    fun checkLocation(onLocationReceived: (Location) -> Unit) {
        if (isLocationEnabled()) {
            requireLocation { location ->
                location?.let { onLocationReceived(it) }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun requireLocation(onComplete: (Location?) -> Unit = {}) {
        val cancelToken = CancellationTokenSource()
        if (isLocationPermissionGranted()) {
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancelToken.token
            ).addOnCompleteListener {
                onComplete(it.result)
            }
        }
    }

    fun isLocationPermissionGranted() =
        ContextCompat.checkSelfPermission(
            activity.applicationContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun isLocationEnabled() =
        (activity.getSystemService(Context.LOCATION_SERVICE) as?
                LocationManager)?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true

}
