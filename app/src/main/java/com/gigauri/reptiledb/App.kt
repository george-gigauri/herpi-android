package com.gigauri.reptiledb

import android.Manifest
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.facebook.FacebookSdk
import com.facebook.ads.AudienceNetworkAds
import com.gigauri.reptiledb.module.common.BuildConfig
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.service.FetchAndCacheDataFromServerService
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltAndroidApp
class App : Application() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.setClientToken(BuildConfig.FACEBOOK_CLIENT_ID)
        FacebookSdk.fullyInitialize()
        AudienceNetworkAds.initialize(this)

        scope.launch {
            if (isNotificationPermissionGranted()) {
                Intent(this@App, FetchAndCacheDataFromServerService::class.java).let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(it)
                    } else {
                        startService(it)
                    }
                }
            } else {
                for (i in 0..15) {
                    delay(2500)
                    if (isNotificationPermissionGranted()) {
                        Intent(this@App, FetchAndCacheDataFromServerService::class.java).let {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                startForegroundService(it)
                            } else {
                                startService(it)
                            }
                        }
                        break
                    }
                }
                job.cancel()
            }
        }
    }

    private fun isNotificationPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        job.cancel()
    }
}