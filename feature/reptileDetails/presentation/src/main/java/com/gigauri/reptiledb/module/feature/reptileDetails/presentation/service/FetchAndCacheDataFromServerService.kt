package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
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
            if (elapsedTime < (1 * Const.Time.ONE_MINUTE)) {
                stop()
                job.cancel()
            }

            if (this@FetchAndCacheDataFromServerService.isOnline()) {
                deleteReptilesFromDatabase.execute()
            } else {
                stop()
                job.cancel()
            }

            var page = 0
            var isRunning = true

            while (isRunning) {
                getAllReptiles.fromRemote(
                    page = page,
                    pageSize = 20,
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
                                                ImageRequest.Builder(this@FetchAndCacheDataFromServerService)
                                                    .data(details.data.transparentThumbnailUrl)
                                                    .diskCachePolicy(CachePolicy.ENABLED)
                                                    .memoryCachePolicy(CachePolicy.ENABLED)
                                                    .networkCachePolicy(CachePolicy.ENABLED)
                                                    .allowConversionToBitmap(true)
                                                    .build().let { builder ->
                                                        this@FetchAndCacheDataFromServerService.imageLoader.enqueue(
                                                            builder
                                                        )
                                                    }

                                                details.data.gallery.map { g ->
                                                    ImageRequest.Builder(this@FetchAndCacheDataFromServerService)
                                                        .data(g.url)
                                                        .diskCachePolicy(CachePolicy.ENABLED)
                                                        .memoryCachePolicy(CachePolicy.DISABLED)
                                                        .networkCachePolicy(CachePolicy.DISABLED)
                                                        .allowConversionToBitmap(true)
                                                        .build().let { builder ->
                                                            this@FetchAndCacheDataFromServerService.imageLoader.enqueue(
                                                                builder
                                                            )
                                                        }
                                                }
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

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}