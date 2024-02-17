package com.gigauri.reptiledb.module.core.presentation.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.R
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorBottomSheet(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    icon: Painter = painterResource(id = R.drawable.default_error_icon),
    positiveButton: @Composable () -> Unit,
    negativeButton: @Composable () -> Unit,
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
        ) {

            // Location Icon
            Icon(
                painter = icon,
                contentDescription = null,
                tint = HerpiColors.NormalRed,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            VerticalMargin(size = 24.dp)

            // Title
            PrimaryTextDarkGray(
                text = title,
                alignment = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)

            )
            VerticalMargin(size = 12.dp)
            // Message
            SecondaryTextLighterDark(
                text = message,
                alignment = TextAlign.Start,
                size = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            VerticalMargin(size = 32.dp)

            // Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    negativeButton()
                }
                HorizontalMargin(size = 12.dp)
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    positiveButton()
                }
            }
        }
    }
}