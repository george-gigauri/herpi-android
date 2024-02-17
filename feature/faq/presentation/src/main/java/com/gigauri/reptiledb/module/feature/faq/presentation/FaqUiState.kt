package com.gigauri.reptiledb.module.feature.faq.presentation

import com.gigauri.reptiledb.module.feature.faq.domain.model.Faq

data class FaqUiState(
    val isLoading: Boolean = false,
    val data: List<Faq> = emptyList(),
    val expandedItemId: Long? = null,
)
