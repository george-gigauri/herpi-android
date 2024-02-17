package com.gigauri.reptiledb.module.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gigauri.reptiledb.module.core.domain.model.Author
import com.gigauri.reptiledb.module.core.domain.model.Family
import com.gigauri.reptiledb.module.core.domain.model.GalleryItem

@Entity(tableName = "reptile_details")
data class ReptileDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Long = -1,
    var name: String = "",
    var scientificName: String = "",
    var description: String = "",
    var genus: String = "",
    var image: String? = null,
    var transparentImageUrl: String? = null,
    var gallery: List<GalleryItem> = emptyList(),
    var addedBy: Author? = null,
    var family: Family? = null,
    var hasRedFlag: Boolean = false,
    var venomous: Boolean = false,
    var hasMildVenom: Boolean = false,
    var type: String = "",
)
