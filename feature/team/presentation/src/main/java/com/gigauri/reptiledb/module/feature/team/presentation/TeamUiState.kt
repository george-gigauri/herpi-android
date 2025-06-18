package com.gigauri.reptiledb.module.feature.team.presentation

import com.gigauri.reptiledb.module.feature.team.domain.model.Team
import com.gigauri.reptiledb.module.feature.team.domain.model.TeamGroupItem

data class TeamUiState(
    val isLoading: Boolean = false,
    val data: List<TeamGroupItem> = emptyList(),
    val expandedItemId: Long = 0
)