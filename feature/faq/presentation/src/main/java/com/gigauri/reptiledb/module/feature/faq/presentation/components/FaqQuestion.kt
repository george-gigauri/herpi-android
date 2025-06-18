package com.gigauri.reptiledb.module.feature.faq.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.faq.domain.model.Faq
import com.gigauri.reptiledb.module.feature.faq.presentation.R

@Composable
fun FaqQuestion(
    data: Faq,
    isExpanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val animateIconRotation: Float by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 180f,
        label = "icon_rotation",
        animationSpec = tween(500)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isExpanded) {
                    HerpiColors.DarkGreenMain
                } else {
                    HerpiColors.White
                }
            )
            .border(1.dp, HerpiColors.LightGray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {

        // Question
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            PrimaryTextDarkGray(
                text = data.question,
                color = if (isExpanded) HerpiColors.White else HerpiColors.DarkGray,
                maxLines = 10,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            // Expanded / Collapsed Icon
            Icon(
                painter = painterResource(id = R.drawable.ic_icon_up),
                contentDescription = null,
                tint = if (isExpanded) HerpiColors.White.copy(alpha = 0.7f) else HerpiColors.Black,
                modifier = Modifier
                    .size(24.dp)
                    .padding(6.dp)
                    .rotate(animateIconRotation)
            )
        }

        if (isExpanded) {
            VerticalMargin(size = 16.dp)
        }

        // Answer
        AnimatedVisibility(
            visible = isExpanded
        ) {
            SecondaryTextLighterDark(
                text = data.answer,
                size = 15.sp,
                color = HerpiColors.White.copy(alpha = 0.75f)
            )
        }
    }
}