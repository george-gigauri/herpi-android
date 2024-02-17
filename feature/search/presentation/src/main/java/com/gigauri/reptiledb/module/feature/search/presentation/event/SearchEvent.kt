package com.gigauri.reptiledb.module.feature.search.presentation.event

sealed class SearchEvent {
    data class KeywordChange(val keyword: String) : SearchEvent()
}