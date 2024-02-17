package com.gigauri.reptiledb.module.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gigauri.reptiledb.module.core.domain.model.Author
import com.gigauri.reptiledb.module.core.domain.model.Family
import com.gigauri.reptiledb.module.core.domain.model.GalleryItem

@Entity(tableName = "reptile_general")
data class ReptileEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var name: String = "",
    var scientificName: String = "",
    var family: Family? = null,
    var genus: String = "",
    var type: String = "",
    var description: String = "",
    var hasRedFlag: Boolean = false,
    var hasMildVenom: Boolean = false,
    var isVenomous: Boolean = false,
    var image: String? = null,
    var transparentThumbnail: String? = null,
    var gallery: List<GalleryItem> = emptyList(),
    var addedBy: Author? = null,
    var views: Int = 0,
    var createdAt: Long = System.currentTimeMillis(),
    var publishedAt: Long = System.currentTimeMillis()
)