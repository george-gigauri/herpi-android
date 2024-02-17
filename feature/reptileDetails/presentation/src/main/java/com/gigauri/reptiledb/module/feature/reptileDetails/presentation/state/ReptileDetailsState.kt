package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.state

import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Distribution
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.ReptileFull

data class ReptileDetailsState(
    val data: ReptileFull? = null,
    val distribution: List<Distribution> = emptyList(),
    val isLoading: Boolean = false,
    val error: ErrorType? = null
)
