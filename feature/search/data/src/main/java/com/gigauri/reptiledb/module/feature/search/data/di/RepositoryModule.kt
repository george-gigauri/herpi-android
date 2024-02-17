package com.gigauri.reptiledb.module.feature.search.data.di

import com.gigauri.reptiledb.module.feature.search.data.repository.SearchRepositoryImpl
import com.gigauri.reptiledb.module.feature.search.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchRepository(repo: SearchRepositoryImpl): SearchRepository
}