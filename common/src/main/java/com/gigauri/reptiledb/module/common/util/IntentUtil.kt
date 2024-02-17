package com.gigauri.reptiledb.module.common.util

import android.app.Activity
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri


object IntentUtil {

    fun shareUrl(content: String, activity: Activity) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, content)
            activity.startActivity(Intent.createChooser(this, "Share Herpi"))
        }
    }

    fun openUrlToBrowserPopup(activity: Activity, url: String?) {
        url?.let {
            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
            val customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(activity, it.toUri())
        }
    }
}