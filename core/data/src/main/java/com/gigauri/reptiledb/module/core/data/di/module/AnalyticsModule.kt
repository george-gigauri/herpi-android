package com.gigauri.reptiledb.module.core.data.di.module

import com.gigauri.reptiledb.module.core.data.repository.FirebaseAnalytics
import com.gigauri.reptiledb.module.core.domain.qualifier.analytics.Firebase
import com.gigauri.reptiledb.module.core.domain.repository.Analytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    @Singleton
    @Firebase
    fun provideFirebaseAnalytics(repo: FirebaseAnalytics): Analytics = repo
}