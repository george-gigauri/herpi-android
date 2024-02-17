package com.gigauri.reptiledb.module.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.DefaultCircularLoader
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.extensions.VenomousLabel

@Composable
fun NearbyReptile(
    reptile: Reptile,
    onClick: () -> Unit
) {

    var isLoading: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(190.dp)
            .heightIn(max = 250.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Box {
            AsyncImage(
                model = reptile.image,
                contentDescription = "Nearby ${reptile.name}",
                contentScale = ContentScale.Crop,
                onState = {
                    isLoading = it is AsyncImagePainter.State.Loading
                },
                modifier = Modifier
                    .size(180.dp, 96.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(HerpiColors.DarkGreenMain)
            )

            if (isLoading) {
                DefaultCircularLoader(modifier = Modifier.align(Alignment.Center))
            }
        }

        VerticalMargin(size = 6.dp)

        PrimaryTextDarkGray(
            text = reptile.name,
            size = 13.sp,
            maxLines = 1,
            modifier = Modifier.align(Alignment.Start)
        )
        VerticalMargin(size = 6.dp)

        reptile.VenomousLabel(
            textSize = 10.sp,
            modifier = Modifier.align(Alignment.Start)
        )
    }
}