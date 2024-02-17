package com.gigauri.reptiledb.module.feature.team.data.di

import com.gigauri.reptiledb.module.feature.team.data.remote.api.TeamApi
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
    fun provideTeamApi(retrofit: Retrofit): TeamApi = retrofit.create()
}