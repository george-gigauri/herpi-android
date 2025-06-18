package com.gigauri.reptiledb.module.feature.faq.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.feature.faq.presentation.components.FaqQuestion
import com.gigauri.reptiledb.module.feature.faq.presentation.components.TopBar

@Composable
fun FaqScreen(
    onDrawerClick: () -> Unit,
    onChatClick: () -> Unit,
) {

    val lazyListState = rememberLazyListState()
    val viewModel: FaqViewModel = hiltViewModel()
    val state: FaqUiState by viewModel.state.collectAsStateWithLifecycle()
    val error: ErrorType? by viewModel.error.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = Unit, block = {
        viewModel.analyticsLogger.logPage("FaqScreen", null)
        viewModel.analyticsLogger.logEvent(Const.Event.OPEN_FAQ, mapOf())
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HerpiColors.DarkGreenMain)
            .statusBarsPadding()
    ) {
        TopBar(
            onDrawerClick = onDrawerClick,
            onChatClick = onChatClick
        )

        // White Container
        LazyColumn(
            contentPadding = PaddingValues(32.dp),
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(HerpiColors.White)
        ) {

            // Title Team
            item {
                PrimaryTextDarkGray(text = stringResource(id = R.string.title_faq))
            }

            // Person List
            item { VerticalMargin(size = 12.dp) }
            items(state.data, key = { it.id }) {
                FaqQuestion(
                    data = it,
                    isExpanded = it.id == state.expandedItemId,
                    onClick = {
                        viewModel.onEvent(FaqUiEvent.Expand(it.id))
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}