package com.gigauri.reptiledb.module.feature.team.presentation

import com.gigauri.reptiledb.module.feature.team.domain.model.Team

data class TeamUiState(
    val isLoading: Boolean = false,
    val data: List<Team> = emptyList(),
    val expandedItemId: Long = 0
)