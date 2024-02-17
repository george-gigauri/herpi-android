package com.gigauri.reptiledb.module.feature.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import com.gigauri.reptiledb.module.core.presentation.HerpiColors

@Composable
fun DrawerTopDecoration(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .then(modifier)
            .drawWithContent {

            }
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(HerpiColors.LightGreen.copy(alpha = 0.1f))
        )

        Spacer(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize(0.75f)
                .background(HerpiColors.LightGreen.copy(alpha = 0.1f))
                .align(Alignment.Center)
        )

        Spacer(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize(0.45f)
                .background(HerpiColors.LightGreen.copy(alpha = 0.1f))
                .align(Alignment.Center)
        )
    }
}