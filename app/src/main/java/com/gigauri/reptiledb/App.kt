package com.gigauri.reptiledb

import android.app.Application
import android.content.Intent
import android.os.Build
import com.facebook.FacebookSdk
import com.facebook.ads.AudienceNetworkAds
import com.gigauri.reptiledb.module.common.BuildConfig
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.service.FetchAndCacheDataFromServerService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.setClientToken(BuildConfig.FACEBOOK_CLIENT_ID)
        FacebookSdk.fullyInitialize()
        AudienceNetworkAds.initialize(this)

        Intent(this, FetchAndCacheDataFromServerService::class.java).let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(it)
            } else {
                startService(it)
            }
        }
    }
}