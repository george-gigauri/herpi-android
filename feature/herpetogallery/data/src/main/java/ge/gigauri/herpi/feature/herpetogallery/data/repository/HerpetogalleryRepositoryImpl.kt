package ge.gigauri.herpi.feature.herpetogallery.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gigauri.reptiledb.module.core.domain.model.ReptileType
import ge.gigauri.herpi.feature.herpetogallery.data.remote.api.HerpetogalleryApi
import ge.gigauri.herpi.feature.herpetogallery.data.remote.datasource.HerpetogalleryPagingSource
import ge.gigauri.herpi.feature.herpetogallery.domain.model.HerpetogalleryItem
import ge.gigauri.herpi.feature.herpetogallery.domain.repository.HerpetogalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HerpetogalleryRepositoryImpl @Inject constructor(
    private val api: HerpetogalleryApi
) : HerpetogalleryRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 12,
        initialLoadSize = 12,
        prefetchDistance = 4
    )

    override fun getGalleryItems(
        category: ReptileType?,
        venom: String?,
        redList: Boolean?
    ): Flow<PagingData<HerpetogalleryItem>> {
        return Pager(
            config = pagingConfig
        ) {
            HerpetogalleryPagingSource(
                api = api,
                category = category,
                venom = venom,
                redList = redList
            )
        }.flow
    }
}
