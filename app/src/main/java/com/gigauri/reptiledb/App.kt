package com.gigauri.reptiledb

import android.Manifest
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.facebook.FacebookSdk
import com.facebook.ads.AudienceNetworkAds
import com.gigauri.reptiledb.module.common.BuildConfig
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.service.FetchAndCacheDataFromServerService
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.workManager.UpdateOfflineDataWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.setClientToken(BuildConfig.FACEBOOK_CLIENT_ID)
        FacebookSdk.fullyInitialize()
        AudienceNetworkAds.initialize(this)

        UpdateOfflineDataWorker.startPeriodicWork(this@App)
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}