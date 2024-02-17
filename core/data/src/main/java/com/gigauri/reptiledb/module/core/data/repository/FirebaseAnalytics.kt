package com.gigauri.reptiledb.module.core.data.repository

import android.os.Bundle
import androidx.core.os.bundleOf
import com.gigauri.reptiledb.module.core.domain.repository.Analytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseAnalytics @Inject constructor() : Analytics {

    private var analytics: FirebaseAnalytics = Firebase.analytics

    override fun logEvent(eventName: String, params: Map<String, Any?>) {
        analytics.logEvent(eventName, bundleOf(*params.toList().toTypedArray()))
    }

    override fun logPage(name: String, c: Class<*>?) {
        analytics.logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.SCREEN_NAME, name,
                )
                putString(
                    FirebaseAnalytics.Param.SCREEN_CLASS, c?.name
                )
            }
        )
    }
}