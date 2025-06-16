package com.gigauri.reptiledb.module.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.DefaultCircularLoader
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray

@Composable
fun Category(
    iconUrl: String,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    var isLoading: Boolean by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .widthIn(min = 96.dp, max = 125.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(90.dp, 95.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (isSelected) HerpiColors.DarkGreenMain
                    else HerpiColors.White
                )
        ) {
            AsyncImage(
                model = iconUrl,
                contentDescription = null,
                onState = {
                    isLoading = it is AsyncImagePainter.State.Loading
                },
                colorFilter = ColorFilter.tint(if (isSelected) HerpiColors.White else HerpiColors.DarkGray),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )

            if (isLoading) {
                DefaultCircularLoader(modifier = Modifier.align(Alignment.Center))
            }
        }

        VerticalMargin(size = 8.dp)

        PrimaryTextDarkGray(
            text = title,
            size = 14.sp,
            color = if (isSelected) HerpiColors.DarkGray else HerpiColors.LightGray
        )
    }
}