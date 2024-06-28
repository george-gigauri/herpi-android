package com.gigauri.reptiledb.module.common.extensions

import android.content.Context
import android.location.Geocoder
import android.location.Location
import java.util.Locale

fun Location?.getCityAndRegion(context: Context, locale: String? = null): String? {
    return try {
        val geocoder = Geocoder(context, Locale(locale ?: Locale.getDefault().language))
        if (this == null) return null

        return geocoder.getFromLocation(latitude, longitude, 1).let { addresses ->
            addresses?.let {
                if (addresses.isNotEmpty()) {
                    val city: String? = try {
                        addresses[0].locality
                    } catch (e: Exception) {
                        null
                    }
                    val state: String? = try {
                        addresses[0].adminArea
                    } catch (e: Exception) {
                        null
                    }
                    val country: String? = try {
                        addresses[0].countryName
                    } catch (e: Exception) {
                        null
                    }

                    "${city?.let { "$it, " } ?: ""} ${state?.let { "$it, " } ?: ""} ${country?.let { "$it " } ?: ""}"
                } else "---"
            } ?: "---"
        }
    } catch (e: Exception) {
        null
    }
}