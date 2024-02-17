package com.gigauri.reptiledb.module.common

import androidx.annotation.StringRes

enum class AppLanguages(
    val code: String,
    @StringRes val titleRes: Int
) {
    GEO(
        "ka",
        R.string.language_georgian
    ),
    ENG(
        "en",
        R.string.language_english
    )
}