package ge.gigauri.herpi.feature.herpetogallery.domain.model

import com.gigauri.reptiledb.module.core.domain.model.Author
import com.gigauri.reptiledb.module.core.domain.model.Reptile

data class HerpetogalleryItem(
    val id: Long,
    val reptile: Reptile,
    val imageId: Long,
    val imageUrl: String,
    val credits: List<String>,
    val author: Author?
)
