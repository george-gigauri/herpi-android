package com.gigauri.reptiledb.module.feature.faq.presentation

sealed class FaqUiEvent {
    data object Load : FaqUiEvent()
    data class Expand(val id: Long) : FaqUiEvent()
}