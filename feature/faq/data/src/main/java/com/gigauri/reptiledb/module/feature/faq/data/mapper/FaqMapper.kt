package com.gigauri.reptiledb.module.feature.faq.data.mapper

import com.gigauri.reptiledb.module.feature.faq.data.local.model.FaqEntity
import com.gigauri.reptiledb.module.feature.faq.data.remote.dto.FaqDto
import com.gigauri.reptiledb.module.feature.faq.domain.model.Faq

fun FaqDto.toDomain(): Faq {
    return Faq(
        id, question, answer
    )
}

fun Faq.toEntity(): FaqEntity {
    return FaqEntity(
        id, question, answer
    )
}

fun FaqEntity.toDomain(): Faq {
    return Faq(
        id, question, answer
    )
}