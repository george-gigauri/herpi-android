package com.gigauri.reptiledb.module.feature.faq.data.di

import com.gigauri.reptiledb.module.feature.faq.data.remote.api.FaqApi
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
    fun provideFaqApi(retrofit: Retrofit): FaqApi = retrofit.create()
}