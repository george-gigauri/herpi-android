package com.gigauri.reptiledb.module.feature.team.presentation

sealed class TeamUiEvent {
    data object Load : TeamUiEvent()
    data class Expand(val itemId: Long) : TeamUiEvent()
}