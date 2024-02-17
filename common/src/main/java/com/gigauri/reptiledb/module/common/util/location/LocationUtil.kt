package com.gigauri.reptiledb.module.common.util.location

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.location.LocationManager
import android.os.Build
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority.PRIORITY_BALANCED_POWER_ACCURACY
import com.google.android.gms.tasks.Task

object LocationUtil {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(context: Context): Location? {
        return (context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager)?.getLastKnownLocation(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @SuppressLint("MissingPermission")
    fun getMyLocation(
        context: Context,
        onSuccess: (Location?) -> Unit = { },
    ) {
        LocationServices.getFusedLocationProviderClient(context)
            .getCurrentLocation(
                CurrentLocationRequest.Builder()
                    .setDurationMillis(2500)
                    .setMaxUpdateAgeMillis(5000)
                    .setPriority(PRIORITY_BALANCED_POWER_ACCURACY)
                    .build(),
                null
            ).addOnSuccessListener(onSuccess)
            .addOnFailureListener {
                it.printStackTrace()
                onSuccess(null)
            }
    }


    fun isLocationTurnedOn(context: Context): Boolean {
        val manager = (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            manager.isLocationEnabled
        } else {
            manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }
    }

    fun requestLocation(context: Activity, onTurnedOn: () -> Unit) {
        val locationRequest: LocationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10 * 1000
        locationRequest.fastestInterval = 5 * 1000

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        builder.setAlwaysShow(true)

        val result: Task<LocationSettingsResponse> =
            LocationServices.getSettingsClient(context).checkLocationSettings(builder.build())

        result.addOnCompleteListener { task ->
            onTurnedOn.invoke()

            try {
                val response = task.getResult(ApiException::class.java)
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                        try {
                            val resolvable = exception as ResolvableApiException
                            resolvable.startResolutionForResult(context, 754)
                        } catch (e: IntentSender.SendIntentException) {
                        } catch (e: ClassCastException) {
                        }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
                }
            }
        }
    }
}