package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.core.presentation.extensions.VenomousLabel
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.ReptileFull

@Composable
fun BasicInfoCard(
    data: ReptileFull,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .then(modifier)
            .zIndex(2f)
            .background(HerpiColors.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {

        // Name and Venomous status
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Name
            PrimaryTextDarkGray(
                text = data.name,
                maxLines = 3,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            )
            // is venomous
            data.VenomousLabel(
                textSize = 13.sp
            )
        }

        // Scientific Name
        SecondaryTextLighterDark(
            text = data.scientificName
        )
        VerticalMargin(size = 6.dp)

        // Family Name
        Text(
            text = data.family.name,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = HerpiColors.DarkGreenMain,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            ),
            modifier = Modifier
        )
        VerticalMargin(size = 16.dp)

        // Is Endangered
        if (data.isEndangered) {
            EndangeredSpecieCard(
                modifier = Modifier.fillMaxWidth()
            )
            VerticalMargin(size = 16.dp)
        }

        // Author
        AuthorPart(
            author = data.author,
            modifier = Modifier.align(Alignment.End)
        )
    }
}