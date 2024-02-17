package com.gigauri.reptiledb.module.core.data.mapper

import com.gigauri.reptiledb.module.core.data.remote.dto.AuthorDto
import com.gigauri.reptiledb.module.core.domain.model.Author

fun AuthorDto.toDomain(): Author {
    return Author(
        id,
        "$firstName $lastName",
        avatar
    )
}