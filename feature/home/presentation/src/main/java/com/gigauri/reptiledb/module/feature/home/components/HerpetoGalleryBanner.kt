package com.gigauri.reptiledb.module.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.R
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin

@Composable
fun HerpetoGalleryBanner(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.LightGray)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(100.dp)
    ) {

        Image(
            painter = painterResource(R.drawable.herpetogallery_thumb),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            HerpiColors.DarkGreenMain,
                            HerpiColors.DarkGreenMain.copy(alpha = 0.85f),
                            HerpiColors.DarkGreenMain.copy(alpha = 0.75f),
                            HerpiColors.DarkGreenMain.copy(alpha = 0.15f),
                        )
                    )
                )
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.title_herpetogallery),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
            )
            VerticalMargin(4.dp)
            Text(
                text = stringResource(R.string.subtitle_herpetogallery),
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 12.sp,
                modifier = Modifier
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HerpetoGalleryBanner(
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}