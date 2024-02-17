package com.gigauri.reptiledb.module.feature.main.presentation

import com.gigauri.reptiledb.module.common.AppLanguages

sealed class MainEvent {
    data class SetLanguage(val language: AppLanguages) : MainEvent()
}