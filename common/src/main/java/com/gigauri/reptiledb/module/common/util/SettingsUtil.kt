package com.gigauri.reptiledb.module.common.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings


object SettingsUtil {

    fun openMessengerChat(activity: Activity) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/reptiledb"))
        activity.startActivity(intent)
    }

    fun openLocationSettings(activity: Activity) {
        val intent: Intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        val uri = Uri.fromParts("package", "com.gigauri.reptiledb", null)
        intent.setData(uri)
        activity.startActivity(intent)
    }

    fun openAppPermissionSettings(activity: Activity) {
        val intent: Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", "com.gigauri.reptiledb", null)
        intent.setData(uri)
        activity.startActivity(intent)
    }
}