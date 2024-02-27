package com.gigauri.reptiledb.module.feature.main.presentation.navigation

import androidx.annotation.StringRes
import com.gigauri.reptiledb.module.feature.main.presentation.R

sealed class Pages(
    @StringRes val titleRes: Int,
    val isAvailableWhenOffline: Boolean,
    val pageId: String
) {

    data object Home : Pages(
        R.string.nav_home,
        true,
        "home"
    )

    data object Contact : Pages(
        R.string.nav_contact,
        false,
        "contact"
    )

    data object Team : Pages(
        R.string.nav_team,
        false,
        "team"
    )

    data object Faq : Pages(
        R.string.nav_faq,
        true,
        "faq"
    )

    data object Search : Pages(
        R.string.hint_search_here,
        false,
        "search"
    )

    data object ReptileDetails : Pages(
        R.string.btn_details,
        true,
        "reptile_details"
    )

    companion object {
        fun all(): List<Pages> = listOf(Home, Contact, Team, Faq)
    }
}
