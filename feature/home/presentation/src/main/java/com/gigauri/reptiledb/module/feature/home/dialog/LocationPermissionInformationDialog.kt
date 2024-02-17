package com.gigauri.reptiledb.module.feature.home.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPermissionInformationDialog(
    onUnderstoodClick: () -> Unit = { },
    onCancel: () -> Unit = { }
) {
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = state,
        onDismissRequest = { onCancel() }
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(bottom = 36.dp)
        ) {

            // Location Icon
            Icon(
                painter = painterResource(id = R.drawable.ic_location_transparent),
                contentDescription = null,
                tint = HerpiColors.DarkGreenMain,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            VerticalMargin(size = 24.dp)

            // Title
            PrimaryTextDarkGray(
                text = stringResource(id = R.string.title_how_we_use_your_location),
                alignment = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)

            )
            VerticalMargin(size = 12.dp)
            // Message
            SecondaryTextLighterDark(
                text = stringResource(id = R.string.message_how_we_use_your_location),
                alignment = TextAlign.Start,
                size = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            VerticalMargin(size = 32.dp)

            // Button
            Text(
                text = stringResource(id = R.string.btn_understood),
                color = HerpiColors.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(HerpiColors.DarkGreenMain)
                    .clickable { onUnderstoodClick() }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .align(Alignment.End)
            )
        }
    }
}