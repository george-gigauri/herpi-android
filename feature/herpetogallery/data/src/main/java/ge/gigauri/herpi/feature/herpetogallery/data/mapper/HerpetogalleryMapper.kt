package ge.gigauri.herpi.feature.herpetogallery.data.mapper

import com.gigauri.reptiledb.module.core.data.mapper.toDomain
import ge.gigauri.herpi.feature.herpetogallery.data.remote.dto.HerpetogalleryItemDto
import ge.gigauri.herpi.feature.herpetogallery.domain.model.HerpetogalleryItem

fun HerpetogalleryItemDto.toDomain(): HerpetogalleryItem {
    return HerpetogalleryItem(
        id = id,
        reptile = reptile.toDomain(),
        imageId = image.id,
        imageUrl = image.url,
        credits = image.credits ?: emptyList(),
        author = image.author?.toDomain()
    )
}
