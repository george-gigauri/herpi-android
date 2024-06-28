package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import coil.executeBlocking
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.SizeResolver
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.common.extensions.isOnline
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.usecase.app.GetDataLastSyncTime
import com.gigauri.reptiledb.module.core.domain.usecase.app.SetDataLastSyncAt
import com.gigauri.reptiledb.module.core.domain.usecase.reptiles.DeleteReptilesFromDatabase
import com.gigauri.reptiledb.module.core.domain.usecase.reptiles.GetAllReptiles
import com.gigauri.reptiledb.module.core.domain.usecase.reptiles.InsertReptilesIntoDatabase
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.usecase.GetDetails
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class FetchAndCacheDataFromServerService : Service() {

    @Inject
    lateinit var getAllReptiles: GetAllReptiles

    @Inject
    lateinit var getReptileDetails: GetDetails

    @Inject
    lateinit var insertReptilesIntoDatabase: InsertReptilesIntoDatabase

    @Inject
    lateinit var deleteReptilesFromDatabase: DeleteReptilesFromDatabase

    @Inject
    lateinit var getDataLastSyncTime: GetDataLastSyncTime

    @Inject
    lateinit var setDataLastSyncTime: SetDataLastSyncAt

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val notificationId = Random.nextInt()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        scope.launch {
            val elapsedTime = System.currentTimeMillis() - getDataLastSyncTime.execute()
            if (elapsedTime < (3 * Const.Time.ONE_WEEK)) {
                stop()
                return@launch
            }

            if (this@FetchAndCacheDataFromServerService.isOnline() && isNotificationPermissionGranted()) {
                deleteReptilesFromDatabase.execute()
            } else {
                stop()
                return@launch
            }

            var page = 0
            var isRunning = true

            while (isRunning) {
                getAllReptiles.fromRemote(
                    page = page,
                    pageSize = 10,
                ).let { result ->
                    when (result) {
                        is Resource.Success -> {
                            if (result.data.isEmpty()) {
                                isRunning = false
                            } else {
                                insertReptilesIntoDatabase.execute(result.data)
                                result.data.map { reptile ->
                                    getReptileDetails.execute(reptile.id).let { details ->
                                        when (details) {
                                            is Resource.Success -> {
                                                // Transparent Thumbnail
                                                ImageRequest.Builder(this@FetchAndCacheDataFromServerService)
                                                    .data(details.data.transparentThumbnailUrl)
                                                    .diskCachePolicy(CachePolicy.ENABLED)
                                                    .memoryCachePolicy(CachePolicy.ENABLED)
                                                    .networkCachePolicy(CachePolicy.ENABLED)
                                                    .allowHardware(false)
                                                    .build().let { builder ->
                                                        this@FetchAndCacheDataFromServerService.imageLoader.execute(
                                                            builder
                                                        ).drawable
                                                    }

                                                // Cover Thumbnail
                                                ImageRequest.Builder(this@FetchAndCacheDataFromServerService)
                                                    .data(details.data.thumbnailUrl)
                                                    .diskCachePolicy(CachePolicy.ENABLED)
                                                    .memoryCachePolicy(CachePolicy.ENABLED)
                                                    .networkCachePolicy(CachePolicy.ENABLED)
                                                    .allowHardware(false)
                                                    .build().let { builder ->
                                                        this@FetchAndCacheDataFromServerService.imageLoader.execute(
                                                            builder
                                                        ).drawable
                                                    }

                                                // Gallery items
                                                details.data.gallery.map { g ->
                                                    async {
                                                        ImageRequest.Builder(this@FetchAndCacheDataFromServerService)
                                                            .data(g.url)
                                                            .diskCachePolicy(CachePolicy.ENABLED)
                                                            .memoryCachePolicy(CachePolicy.ENABLED)
                                                            .networkCachePolicy(CachePolicy.ENABLED)
                                                            .allowHardware(false)
                                                            .build().let { builder ->
                                                                this@FetchAndCacheDataFromServerService.imageLoader.execute(
                                                                    builder
                                                                ).drawable
                                                            }
                                                    }
                                                }.awaitAll()

                                            }

                                            else -> Unit
                                        }
                                    }
                                }
                            }
                        }

                        else -> isRunning = false
                    }
                }

                page++
            }

            setDataLastSyncTime.execute(System.currentTimeMillis())
            stop()
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(notificationId, createNotification())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                Const.Notification.Channel.AUTO_UPDATE_OFFLINE_DATA,
                "Update Data For Offline Usage",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(
            this,
            Const.Notification.Channel.AUTO_UPDATE_OFFLINE_DATA,
        )
            .setContentTitle(getString(R.string.title_updating_data_for_offline))
            .setContentText(getString(R.string.subtitle_updating_data_for_offline))
            .setSmallIcon(R.drawable.ic_herpi)
            .build()
    }

    private fun stop() {
        stopSelf()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        } else stopForeground(true)
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

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}