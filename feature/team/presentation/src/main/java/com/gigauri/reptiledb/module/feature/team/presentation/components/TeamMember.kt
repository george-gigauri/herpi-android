package com.gigauri.reptiledb.module.feature.team.presentation.components

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.team.domain.model.SocialNetwork
import com.gigauri.reptiledb.module.feature.team.domain.model.Team
import com.gigauri.reptiledb.module.feature.team.presentation.R

@Composable
fun TeamMember(
    context: Context,
    data: Team,
    isExpanded: Boolean,
    onEmailClick: () -> Unit,
    onClick: () -> Unit,
    onSocialClick: (SocialNetwork) -> Unit,
    modifier: Modifier
) {
    val animateIconRotation: Float by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 180f,
        label = "icon_rotation",
        animationSpec = tween(500)
    )

    val animateBorderWidth by animateDpAsState(
        targetValue = if (isExpanded) 2.dp else (-1).dp,
        label = "border_width"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .background(HerpiColors.White)
            .border(
                BorderStroke(
                    animateBorderWidth,
                    HerpiColors.DarkGreenMain
                ),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            // Avatar
            AsyncImage(
                model = data.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
            )

            HorizontalMargin(size = 16.dp)

            // Column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                VerticalMargin(size = 4.dp)

                // Full Name
                PrimaryTextDarkGray(
                    text = "${data.firstName} ${data.lastName}",
                    size = 15.sp,
                    weight = FontWeight.Bold
                )
                VerticalMargin(size = 4.dp)
                // Email
                PrimaryTextDarkGray(
                    text = data.email,
                    size = 12.sp,
                    color = HerpiColors.DarkGreenMain,
                    modifier = Modifier.clickable { onEmailClick() }
                )
                VerticalMargin(size = 8.dp)
                // Role
                SecondaryTextLighterDark(
                    text = data.role,
                    size = 14.sp,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }

            // Expanded / Collapsed Icon
            Icon(
                painter = painterResource(id = R.drawable.ic_icon_up),
                contentDescription = null,
                tint = HerpiColors.DarkGray,
                modifier = Modifier
                    .size(14.dp)
                    .rotate(animateIconRotation)
            )
        }

        if (isExpanded) {
            VerticalMargin(size = 16.dp)
        }

        // Social Networks
        AnimatedVisibility(visible = isExpanded) {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {

                items(data.socialNetworks) {
                    AsyncImage(
                        model = it.networkLogo,
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .clickable { onSocialClick(it) }
                    )
                    HorizontalMargin(size = 8.dp)
                }
            }
        }
    }
}