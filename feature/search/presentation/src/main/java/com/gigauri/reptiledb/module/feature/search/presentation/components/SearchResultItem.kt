package com.gigauri.reptiledb.module.feature.search.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.core.presentation.extensions.VenomousLabel

@Composable
fun SearchResultItem(
    reptile: Reptile,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {

        // Thumbnail
        AsyncImage(
            model = reptile.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(92.dp)
                .aspectRatio(1.55f)
                .clip(RoundedCornerShape(12.dp))
                .background(HerpiColors.White)
        )
        HorizontalMargin(size = 12.dp)
        Column {

            PrimaryTextDarkGray(
                text = reptile.name,
                size = 14.sp
            )
            SecondaryTextLighterDark(
                text = reptile.scientificName,
                size = 14.sp,
            )

            VerticalMargin(size = 8.dp)

            reptile.VenomousLabel()
        }
    }

}