package com.gigauri.reptiledb

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.facebook.FacebookSdk
import com.facebook.ads.AudienceNetworkAds
import com.gigauri.reptiledb.module.common.BuildConfig
import com.gigauri.reptiledb.module.common.extensions.wrapLocale
import com.gigauri.reptiledb.module.core.domain.common.LocaleManager
import com.gigauri.reptiledb.module.core.domain.usecase.app.GetAppLanguage
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.service.FetchAndCacheDataFromServerService
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.workManager.UpdateOfflineDataWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var getAppLanguage: GetAppLanguage

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.setClientToken(BuildConfig.FACEBOOK_CLIENT_ID)
        FacebookSdk.fullyInitialize()
        AudienceNetworkAds.initialize(this)

        UpdateOfflineDataWorker.startPeriodicWork(this@App)

        // Preload locale into static memory
        CoroutineScope(Dispatchers.Default).launch {
            val lang = getAppLanguage.execute().firstOrNull() ?: "ka"
            LocaleManager.currentLang = lang
        }
    }

    override fun attachBaseContext(base: Context) {
        val context = base.wrapLocale(Locale(LocaleManager.currentLang))
        super.attachBaseContext(context)
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}