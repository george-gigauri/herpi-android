package com.gigauri.reptiledb.module.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.home.R
import com.gigauri.reptiledb.module.feature.home.util.venomousTitle

@Composable
fun Reptile(
    reptile: Reptile,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable { onClick() }
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(HerpiColors.White)
    ) {
        // Basic Info
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
        ) {

            // Venomous Status
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = null,
                    tint = if (reptile.venomous) {
                        if (reptile.hasMildVenom) HerpiColors.OrangeYellow
                        else HerpiColors.NormalRed
                    } else if (reptile.hasMildVenom) {
                        HerpiColors.OrangeYellow
                    } else {
                        HerpiColors.DarkGreenMain
                    },
                    modifier = Modifier.size(14.dp)
                )
                HorizontalMargin(size = 4.dp)
                Text(
                    text = reptile.venomousTitle(),
                    fontSize = 12.sp,
                    color = HerpiColors.LightGray,
                )
            }

            Column {
                // Family
                SecondaryTextLighterDark(
                    text = reptile.family?.name ?: "",
                    size = 12.sp
                )

                // Name
                PrimaryTextDarkGray(
                    text = reptile.name,
                    size = 15.sp,
                    maxLines = 3
                )
            }

            // Button Details
            Text(
                text = stringResource(id = R.string.btn_details),
                fontSize = 12.sp,
                color = HerpiColors.White,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(HerpiColors.DarkGreenMain)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }

        // Image
        AsyncImage(
            model = reptile.transparentImage ?: reptile.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(136.dp)
                .fillMaxHeight()
        )
    }
}