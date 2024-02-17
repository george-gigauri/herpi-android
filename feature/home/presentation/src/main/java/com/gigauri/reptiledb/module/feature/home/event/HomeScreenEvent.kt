package com.gigauri.reptiledb.module.feature.home.event

import android.location.Location
import com.gigauri.reptiledb.module.feature.home.domain.model.Category

sealed class HomeScreenEvent {
    data object Load : HomeScreenEvent()
    data class SelectCategory(val category: Category) : HomeScreenEvent()
    data class UpdateLocation(
        val location: Location?,
        val region: String?
    ) : HomeScreenEvent()

    data object ClearErrors : HomeScreenEvent()
}