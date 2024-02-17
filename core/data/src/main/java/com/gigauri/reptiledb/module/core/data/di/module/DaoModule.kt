package com.gigauri.reptiledb.module.core.data.di.module

import com.gigauri.reptiledb.module.core.data.local.dao.CategoryDao
import com.gigauri.reptiledb.module.core.data.local.dao.ReptileDao
import com.gigauri.reptiledb.module.core.data.local.dao.ReptileDetailsDao
import com.gigauri.reptiledb.module.core.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun reptileDao(db: AppDatabase): ReptileDao = db.reptileDao()

    @Provides
    @Singleton
    fun categoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Provides
    @Singleton
    fun provideReptileDetailsDao(db: AppDatabase): ReptileDetailsDao = db.reptileDetailsDao()
}