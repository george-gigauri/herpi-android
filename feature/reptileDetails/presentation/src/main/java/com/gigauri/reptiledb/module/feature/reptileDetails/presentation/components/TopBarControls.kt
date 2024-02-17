package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors

@Composable
fun TopBarControls(
    onBack: () -> Unit,
    onShare: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        // Back
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = HerpiColors.White,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .clickable { onBack() }
                .padding(16.dp)
                .align(Alignment.TopStart)
        )

        // Share
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = null,
            tint = HerpiColors.White,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .clickable { onShare() }
                .padding(16.dp)
                .align(Alignment.TopEnd)
        )
    }
}