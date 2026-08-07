package ge.gigauri.herpi.feature.herpetogallery.domain.repository

import androidx.paging.PagingData
import com.gigauri.reptiledb.module.core.domain.model.ReptileType
import ge.gigauri.herpi.feature.herpetogallery.domain.model.HerpetogalleryItem
import kotlinx.coroutines.flow.Flow

interface HerpetogalleryRepository {
    fun getGalleryItems(
        category: ReptileType? = null,
        venom: String? = null,
        redList: Boolean? = null
    ): Flow<PagingData<HerpetogalleryItem>>
}
