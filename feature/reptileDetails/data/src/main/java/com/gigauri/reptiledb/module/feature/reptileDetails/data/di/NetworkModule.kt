package com.gigauri.reptiledb.module.feature.reptileDetails.data.di

import com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.api.ReptileDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideReptileDetailsApi(retrofit: Retrofit): ReptileDetailsApi = retrofit.create()
}