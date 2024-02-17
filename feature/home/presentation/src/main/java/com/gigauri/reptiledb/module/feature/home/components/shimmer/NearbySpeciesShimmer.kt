package com.gigauri.reptiledb.module.feature.home.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun NearbySpeciesShimmer(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(190.dp)
            .heightIn(max = 250.dp)
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .shimmer()
    ) {
        Spacer(
            modifier = Modifier
                .size(180.dp, 96.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(HerpiColors.LightGray)
                .shimmer()
        )

        VerticalMargin(size = 6.dp)

        Spacer(
            modifier = Modifier
                .width(100.dp)
                .height(18.dp)
                .align(Alignment.Start)
                .clip(RoundedCornerShape(100))
                .background(HerpiColors.LightGray)
                .shimmer()
        )
        VerticalMargin(size = 6.dp)

        Spacer(
            modifier = Modifier
                .width(60.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(100))
                .background(HerpiColors.LightGray)
                .align(Alignment.Start)
                .shimmer()
        )
    }
}