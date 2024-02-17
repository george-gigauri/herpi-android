package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.gigauri.reptiledb.module.core.presentation.HerpiColors

@Composable
fun ReptileThumbnail(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(HerpiColors.CreamyYellow)
        )

        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            HerpiColors.Black.copy(alpha = 0.2f),
                            HerpiColors.Black.copy(alpha = 0.4f),
                            HerpiColors.Black.copy(alpha = 0.8f),
                        )
                    )
                )
        )
    }
}