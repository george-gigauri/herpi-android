package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.workManager

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
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
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@HiltWorker
class UpdateOfflineDataWorker @AssistedInject constructor(
    private val getAllReptiles: GetAllReptiles,
    private val getReptileDetails: GetDetails,
    private val insertReptilesIntoDatabase: InsertReptilesIntoDatabase,
    private val deleteReptilesFromDatabase: DeleteReptilesFromDatabase,
    private val getDataLastSyncTime: GetDataLastSyncTime,
    private val setDataLastSyncTime: SetDataLastSyncAt,
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        val elapsedTime = System.currentTimeMillis() - getDataLastSyncTime.execute()
        if (elapsedTime < (1 * Const.Time.ONE_WEEK)) {
            return Result.retry()
        }

        deleteReptilesFromDatabase.execute()

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
                                            ImageRequest.Builder(context)
                                                .data(details.data.transparentThumbnailUrl)
                                                .diskCachePolicy(CachePolicy.ENABLED)
                                                .memoryCachePolicy(CachePolicy.ENABLED)
                                                .networkCachePolicy(CachePolicy.ENABLED)
                                                .allowHardware(false)
                                                .build().let { builder ->
                                                    context.imageLoader.execute(
                                                        builder
                                                    ).drawable
                                                }

                                            // Cover Thumbnail
                                            ImageRequest.Builder(context)
                                                .data(details.data.thumbnailUrl)
                                                .diskCachePolicy(CachePolicy.ENABLED)
                                                .memoryCachePolicy(CachePolicy.ENABLED)
                                                .networkCachePolicy(CachePolicy.ENABLED)
                                                .allowHardware(false)
                                                .build().let { builder ->
                                                    context.imageLoader.execute(
                                                        builder
                                                    ).drawable
                                                }

                                            // Gallery items
                                            withContext(Dispatchers.IO) {
                                                details.data.gallery.map { g ->
                                                    async {
                                                        ImageRequest.Builder(context)
                                                            .data(g.url)
                                                            .diskCachePolicy(CachePolicy.ENABLED)
                                                            .memoryCachePolicy(CachePolicy.ENABLED)
                                                            .networkCachePolicy(CachePolicy.ENABLED)
                                                            .allowHardware(false)
                                                            .build().let { builder ->
                                                                context.imageLoader.execute(
                                                                    builder
                                                                ).drawable
                                                            }
                                                    }
                                                }.awaitAll()
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
        return Result.success()
    }

    companion object {
        fun startPeriodicWork(context: Context) {
            val periodicWorkRequest = PeriodicWorkRequestBuilder<UpdateOfflineDataWorker>(
                3, TimeUnit.DAYS,
            ).setInitialDelay(1, TimeUnit.SECONDS)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresBatteryNotLow(true)
                        .setRequiresStorageNotLow(true)
                        .build()
                )
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "update_offline_data",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )
        }
    }
}