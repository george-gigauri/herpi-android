package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.gigauri.reptiledb.module.core.domain.model.GalleryItem
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.DefaultCircularLoader

@Composable
fun GalleryPicture(
    item: GalleryItem,
    onClick: () -> Unit,
) {

    var isLoading: Boolean by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .width(220.dp)
            .height(120.dp)
            .clickable { onClick() }
            .padding(end = 12.dp)
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {

        // Image
        AsyncImage(
            model = item.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onState = {
                isLoading = it is AsyncImagePainter.State.Loading
            },
            modifier = Modifier.fillMaxSize()
        )

        // Overlay
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            HerpiColors.DarkerGreenMain.copy(alpha = 0.1f),
                            HerpiColors.DarkerGreenMain.copy(alpha = 0.4f),
                            HerpiColors.DarkerGreenMain.copy(alpha = 0.7f),
                        )
                    )
                )
        )

        // Credits
        Text(
            text = "(C) ${
                if (item.credits.isEmpty()) item.author?.name
                else item.credits.joinToString(", ")
            }",
            color = HerpiColors.White,
            fontSize = 10.sp,
            maxLines = 1,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            ),
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )

        // Loading
        if (isLoading) {
            DefaultCircularLoader(modifier = Modifier.align(Alignment.Center))
        }
    }
}