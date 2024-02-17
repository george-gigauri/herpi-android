package com.gigauri.reptiledb.module.feature.main.presentation.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.common.AppLanguages
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.main.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseApplicationLanguageSheet(
    languages: List<AppLanguages>,
    onRemember: (item: AppLanguages) -> Unit,
    onCancel: () -> Unit
) {
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedLanguage by rememberSaveable { mutableStateOf("") }

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

            // Title
            PrimaryTextDarkGray(
                text = stringResource(id = R.string.title_select_language),
                alignment = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)

            )
            VerticalMargin(size = 12.dp)
            // Message
            SecondaryTextLighterDark(
                text = stringResource(id = R.string.subtitle_select_language),
                alignment = TextAlign.Center,
                size = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            VerticalMargin(size = 32.dp)

            // List of Languages
            languages.map {
                Text(
                    text = stringResource(id = it.titleRes),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HerpiColors.DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (it.code == selectedLanguage) HerpiColors.OrangeYellow
                            else HerpiColors.DarkGray.copy(alpha = 0.2f)
                        )
                        .clickable { selectedLanguage = it.code }
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
                VerticalMargin(size = 8.dp)
            }

            VerticalMargin(size = 16.dp)

            // Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Check Settings Button
                Text(
                    text = stringResource(id = R.string.btn_remember),
                    color = HerpiColors.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .background(HerpiColors.DarkGreenMain)
                        .clickable {
                            languages
                                .find {
                                    selectedLanguage == it.code
                                }
                                ?.let(onRemember)
                        }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .weight(1f)
                )
            }
        }
    }
}