package com.gigauri.reptiledb.module.feature.main.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.common.extensions.loadLocate
import com.gigauri.reptiledb.module.core.presentation.HerpiDefaultTheme
import com.google.android.gms.tasks.Task
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isFlexibleUpdateAllowed
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import android.content.Intent
import android.net.Uri
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.gigauri.reptiledb.module.common.extensions.wrapLocale
import com.gigauri.reptiledb.module.core.domain.common.LocaleManager
import com.gigauri.reptiledb.module.core.domain.usecase.app.GetAppLanguage
import org.osmdroid.config.Configuration
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var appUpdateManager: AppUpdateManager
    private lateinit var appUpdateInfoTask: Task<AppUpdateInfo>

    private val appUpdateListener = InstallStateUpdatedListener { state ->
        if (state.installStatus() == InstallStatus.DOWNLOADED) {
            appUpdateManager.completeUpdate()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInAppUpdate()

        Configuration.getInstance()
            .load(this, PreferenceManager.getDefaultSharedPreferences(this))
        Configuration.getInstance().userAgentValue = "ge.herpi"

        setContent {
            HerpiDefaultTheme {
                MainScreen(intent, viewModel)
            }
        }
    }

    private fun initInAppUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(this)
        appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateManager.registerListener(appUpdateListener)
    }

    private fun checkForUpdate() {
        //  if (!BuildConfig.DEBUG) {
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                viewModel.analytics.logEvent(Const.Event.SHOW_UPDATE_POPUP, mapOf())
                if (appUpdateInfo.isFlexibleUpdateAllowed) {
                    startFlexibleInAppUpdate(appUpdateInfo)
                }
                if (appUpdateInfo.isImmediateUpdateAllowed) {
                    startImmediateInAppUpdate(appUpdateInfo)
                }
            }
        }
        // }
    }

    private fun startFlexibleInAppUpdate(appUpdateInfo: AppUpdateInfo) {
        viewModel.analytics.logEvent(Const.Event.START_FLEXIBLE_UPDATE, mapOf())
        appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            AppUpdateType.FLEXIBLE,
            this,
            798512
        )
    }

    private fun startImmediateInAppUpdate(appUpdateInfo: AppUpdateInfo) {
        viewModel.analytics.logEvent(Const.Event.START_IMMEDIATE_UPDATE, mapOf())
        appUpdateManager.startUpdateFlow(
            appUpdateInfo,
            this,
            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
        )
    }

    override fun attachBaseContext(newBase: Context) {
        val context = newBase.wrapLocale(Locale(LocaleManager.currentLang))
        super.attachBaseContext(context)
    }

    override fun onStart() {
        super.onStart()
        viewModel.analytics.logPage(this.javaClass.simpleName, this.javaClass)
        checkForUpdate()

        val prevLanguage = runBlocking { viewModel.language.firstOrNull() }
        lifecycleScope.launch {
            viewModel.language.collectLatest {
                if (prevLanguage != it) {
                    this@MainActivity.recreate()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appUpdateManager.unregisterListener(appUpdateListener)
    }
}