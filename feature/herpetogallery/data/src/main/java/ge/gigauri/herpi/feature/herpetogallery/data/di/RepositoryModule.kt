package ge.gigauri.herpi.feature.herpetogallery.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.gigauri.herpi.feature.herpetogallery.data.repository.HerpetogalleryRepositoryImpl
import ge.gigauri.herpi.feature.herpetogallery.domain.repository.HerpetogalleryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHerpetogalleryRepository(
        impl: HerpetogalleryRepositoryImpl
    ): HerpetogalleryRepository
}
