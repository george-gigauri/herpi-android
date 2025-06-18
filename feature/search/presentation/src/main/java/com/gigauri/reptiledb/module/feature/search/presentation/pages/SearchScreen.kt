package com.gigauri.reptiledb.module.feature.search.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.feature.search.presentation.R
import com.gigauri.reptiledb.module.feature.search.presentation.components.SearchResultItem
import com.gigauri.reptiledb.module.feature.search.presentation.components.TopBar
import com.gigauri.reptiledb.module.feature.search.presentation.event.SearchEvent
import com.gigauri.reptiledb.module.feature.search.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onReptileClick: (Reptile) -> Unit
) {

    val viewModel: SearchViewModel = hiltViewModel()
    val pagingData = viewModel.pagingData.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.analytics.logEvent(
            Const.Event.OPEN_SEARCH, mapOf()
        )
        viewModel.analytics.logPage(
            "SearchScreen", null
        )
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HerpiColors.DarkGreenMain)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // TopBar
            TopBar(
                onBackClick = onBackClick,
                onSearchTextChange = {
                    viewModel.onEvent(SearchEvent.KeywordChange(it))
                }
            )

            // White Container
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(HerpiColors.LightWindowBg)
                    .padding(horizontal = 16.dp)
            ) {
                item { VerticalMargin(size = 16.dp) }

                item {
                    PrimaryTextDarkGray(
                        text = stringResource(id = R.string.label_search_results),
                    )
                }

                item { VerticalMargin(size = 16.dp) }

                items(pagingData.itemCount, key = { pagingData[it]?.id ?: -1 }) { pos ->
                    pagingData[pos]?.let { item ->
                        SearchResultItem(
                            reptile = item,
                            onClick = { onReptileClick(item) }
                        )
                        VerticalMargin(size = 12.dp)
                    }
                }

                item { VerticalMargin(size = 16.dp) }
            }
        }
    }
}