package com.gigauri.reptiledb

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.ads.AudienceNetworkAds
import com.gigauri.reptiledb.module.common.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        FacebookSdk.setClientToken(BuildConfig.FACEBOOK_CLIENT_ID)
        AudienceNetworkAds.initialize(this)
    }
}