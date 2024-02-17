package com.gigauri.reptiledb.module.core.domain.usecase.analytics

import com.gigauri.reptiledb.module.core.domain.qualifier.analytics.Firebase
import com.gigauri.reptiledb.module.core.domain.repository.Analytics
import javax.inject.Inject

class AnalyticsLogger @Inject constructor(
    @Firebase private val analytics: Analytics
) {

    fun logEvent(eventName: String, params: Map<String, Any?>) {
        analytics.logEvent(eventName, params)
    }

    fun logPage(name: String, c: Class<*>?) {
        analytics.logPage(name, c)
    }
}