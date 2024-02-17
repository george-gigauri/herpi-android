package com.gigauri.reptiledb.module.feature.faq.data.di

import com.gigauri.reptiledb.module.feature.faq.data.repository.FaqRepositoryImpl
import com.gigauri.reptiledb.module.feature.faq.domain.repository.FaqRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFaqRepository(repo: FaqRepositoryImpl): FaqRepository
}