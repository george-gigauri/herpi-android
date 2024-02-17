package com.gigauri.reptiledb.module.feature.home.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.valentinilk.shimmer.shimmer

@Composable
fun CategoryShimmer(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .then(modifier)
            .clip(RoundedCornerShape(12.dp))
            .widthIn(min = 96.dp, max = 125.dp)
            .shimmer()
    ) {
        Box(
            modifier = Modifier
                .size(90.dp, 80.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(HerpiColors.LightGray)
                .shimmer()
        )

        VerticalMargin(size = 8.dp)

        Spacer(
            modifier = Modifier
                .width(70.dp)
                .height(16.dp)
                .background(HerpiColors.LightGray, RoundedCornerShape(100))
        )
    }
}