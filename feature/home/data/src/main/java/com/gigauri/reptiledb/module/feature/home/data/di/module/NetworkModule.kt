package com.gigauri.reptiledb.module.feature.home.data.di.module

import com.gigauri.reptiledb.module.feature.home.data.remote.api.CategoryService
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
    fun provideCategoryApi(retrofit: Retrofit): CategoryService = retrofit.create()
}