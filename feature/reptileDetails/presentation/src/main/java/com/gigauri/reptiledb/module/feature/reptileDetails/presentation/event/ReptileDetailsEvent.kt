package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.event

sealed class ReptileDetailsEvent {
    data class Load(val defaultReptileId: Long? = null) : ReptileDetailsEvent()
    data object ClearErrors : ReptileDetailsEvent()
}