package ge.gigauri.herpi.feature.herpetogallery.domain.usecase

import androidx.paging.PagingData
import com.gigauri.reptiledb.module.core.domain.model.ReptileType
import ge.gigauri.herpi.feature.herpetogallery.domain.model.HerpetogalleryItem
import ge.gigauri.herpi.feature.herpetogallery.domain.repository.HerpetogalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGalleryItemsUseCase @Inject constructor(
    private val repository: HerpetogalleryRepository
) {
    operator fun invoke(
        category: ReptileType? = null,
        venom: String? = null,
        redList: Boolean? = null
    ): Flow<PagingData<HerpetogalleryItem>> {
        return repository.getGalleryItems(category, venom, redList)
    }
}
