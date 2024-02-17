package com.gigauri.reptiledb.module.feature.faq.data.di

import com.gigauri.reptiledb.module.feature.faq.data.local.FaqDatabase
import com.gigauri.reptiledb.module.feature.faq.data.local.dao.FaqDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideFaqDao(db: FaqDatabase): FaqDao = db.dao()
}