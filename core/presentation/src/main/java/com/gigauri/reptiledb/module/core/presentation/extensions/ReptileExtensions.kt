package com.gigauri.reptiledb.module.core.presentation.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.R
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.ReptileFull

@Composable
fun Reptile.VenomousLabel(
    textSize: TextUnit = 12.sp,
    modifier: Modifier = Modifier
) {
    Text(
        text = if (venomous) {
            if (hasMildVenom) {
                stringResource(id = R.string.label_mildly_venomous)
            } else {
                stringResource(id = R.string.label_venomous)
            }
        } else if (hasMildVenom) {
            stringResource(id = R.string.label_mildly_venomous)
        } else {
            stringResource(id = R.string.label_non_venomous)
        },
        fontSize = textSize,
        fontWeight = FontWeight.SemiBold,
        color = HerpiColors.White,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .then(modifier)
            .clip(RoundedCornerShape(100))
            .background(
                if (venomous) {
                    if (hasMildVenom) {
                        HerpiColors.OrangeYellow
                    } else {
                        HerpiColors.NormalRed
                    }
                } else if (hasMildVenom) {
                    HerpiColors.OrangeYellow
                } else {
                    HerpiColors.LightGreen
                }
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    )
}

@Composable
fun ReptileFull.VenomousLabel(
    textSize: TextUnit = 12.sp,
    modifier: Modifier = Modifier
) {
    Text(
        text = if (isVenomous) {
            if (hasMildVenom) {
                stringResource(id = R.string.label_mildly_venomous)
            } else {
                stringResource(id = R.string.label_venomous)
            }
        } else if (hasMildVenom) {
            stringResource(id = R.string.label_mildly_venomous)
        } else {
            stringResource(id = R.string.label_non_venomous)
        },
        fontSize = textSize,
        fontWeight = FontWeight.SemiBold,
        color = HerpiColors.White,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .then(modifier)
            .clip(RoundedCornerShape(100))
            .background(
                if (isVenomous) {
                    if (hasMildVenom) {
                        HerpiColors.OrangeYellow
                    } else {
                        HerpiColors.NormalRed
                    }
                } else if (hasMildVenom) {
                    HerpiColors.OrangeYellow
                } else {
                    HerpiColors.LightGreen
                }
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    )
}