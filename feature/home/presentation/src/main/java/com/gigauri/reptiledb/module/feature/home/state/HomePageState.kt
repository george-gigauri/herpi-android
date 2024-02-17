package com.gigauri.reptiledb.module.feature.home.state

import android.location.Location
import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.feature.home.domain.model.Category

data class HomePageState(
    val isInit: Boolean = false,
    val categories: List<Category> = emptyList(),
    val nearbyReptiles: List<Reptile> = emptyList(),
    val allReptiles: List<Reptile> = emptyList(),
    val isCategoriesLoading: Boolean = false,
    val isNearbyLoading: Boolean = false,
    val isAllReptilesLoading: Boolean = false,
    val region: String? = null,
    val location: Location? = null,
    val error: ErrorType? = null
)
