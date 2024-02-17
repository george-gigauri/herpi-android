package com.gigauri.reptiledb.module.common

object Const {
    const val HERPI_URL = "https://herpi.ge"
    const val BASE_URL = "https://api.herpi.ge/"

    object Key {
        const val KEY_REPTILE_ID = "reptile_id"
    }

    object Url {
        const val CONTACT = "$HERPI_URL/contact"
    }

    object Event {
        const val SELECT_CATEGORY = "select_category" // category_id (SNAKE, LIZARD...)
        const val SET_LANGUAGE = "set_language" // language_code (ka, en...)
        const val NEARBY_SPECIES_NOT_FOUND = "nearby_species_not_found"  // reason
        const val OPEN_NEARBY_SPECIE_DETAILS = "open_nearby_specie_details"  // specie_id
        const val OPEN_IN_OFFLINE_MODE = "open_in_offline_mode"
        const val OPEN_DETAILS = "open_details" // specie_id
        const val OPEN_FAQ = "open_faq"
        const val OPEN_TEAM = "open_team"
        const val OPEN_SEARCH = "open_search"
        const val OPEN_CONTACT = "open_contact"
        const val CLICK_TEAM_EMAIL = "click_team_email" // member
        const val CLICK_TEAM_SOCIAL = "click_team_social_link" // member, network
        const val CLICK_CHAT = "click_chat"
        const val CLICK_PICK_LOCATION_MANUALLY = "click_pick_location_manually"
        const val PICK_LOCATION_MANUALLY = "pick_location_manually"
        const val EXPAND_DISTRIBUTION_MAP = "expand_distribution_map"
        const val SHOW_UPDATE_POPUP = "show_update_available"
        const val START_IMMEDIATE_UPDATE = "start_immediate_update"
        const val START_FLEXIBLE_UPDATE = "start_flexible_update"
        const val SHARE = "share" // specie_id
    }
}