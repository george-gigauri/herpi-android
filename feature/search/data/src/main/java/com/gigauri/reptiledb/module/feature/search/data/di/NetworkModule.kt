package com.gigauri.reptiledb.module.feature.search.data.di

import com.gigauri.reptiledb.module.feature.search.data.remote.api.SearchApi
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
    fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create()
}