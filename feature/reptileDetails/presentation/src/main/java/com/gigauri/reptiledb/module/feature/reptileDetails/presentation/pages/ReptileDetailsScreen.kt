package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.pages

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.common.util.IntentUtil
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.bottomsheet.ErrorBottomSheet
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.R
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components.BasicInfoCard
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components.DistributionCard
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components.GalleryPicture
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components.ReptileThumbnail
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components.TopBarControls
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.dialog.ExpandedMapDialog
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.event.ReptileDetailsEvent
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.viewModels.ReptileDetailsViewModel
import ge.herpi.imageviewer.FullScreenImageViewer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

@Composable
fun ReptileDetailsScreen(
    viewModel: ReptileDetailsViewModel,
    deepLinkId: Long? = null,
    onBackClick: () -> Unit = { }
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isImageViewer by rememberSaveable { mutableStateOf(false) }
    var currentOpenedImageIndex by rememberSaveable { mutableIntStateOf(0) }
    var isMapExpanded by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.onEvent(ReptileDetailsEvent.Load(deepLinkId))
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HerpiColors.LightWindowBg)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // Thumbnail
            ReptileThumbnail(
                imageUrl = state.data?.thumbnailUrl,
                modifier = Modifier.height(375.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 275.dp)
            ) {
                // Basic Info card
                state.data?.let {
                    BasicInfoCard(
                        data = it,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    VerticalMargin(size = 24.dp)
                }

                // Description
                PrimaryTextDarkGray(
                    text = stringResource(id = R.string.title_description),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                VerticalMargin(size = 12.dp)
                SecondaryTextLighterDark(
                    text = state.data?.description ?: "---",
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                VerticalMargin(size = 16.dp)

                // Gallery List
                state.data?.gallery?.let { gallery ->
                    PrimaryTextDarkGray(
                        text = stringResource(id = R.string.title_gallery),
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    VerticalMargin(size = 4.dp)
                    /* LIST */
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp),
                        content = {
                            items(gallery, key = { it.id }) { item ->
                                GalleryPicture(
                                    item = item,
                                    onClick = {
                                        currentOpenedImageIndex =
                                            state.data?.gallery?.indexOf(item) ?: 0
                                        isImageViewer = true
                                    }
                                )
                            }
                        }
                    )
                    VerticalMargin(size = 12.dp)
                }

                // Coverage Overview
                PrimaryTextDarkGray(
                    text = stringResource(id = R.string.title_coverage_areas),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                /* MAP */
                DistributionCard(
                    data = state.distribution,
                    onTouch = {
                        isMapExpanded = true
                        viewModel.analytics.logEvent(
                            Const.Event.EXPAND_DISTRIBUTION_MAP,
                            mapOf("specie_id" to state.data?.id)
                        )
                    },
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                VerticalMargin(size = 8.dp)
                // Map Button
                Text(
                    text = stringResource(id = R.string.btn_expand_map),
                    color = HerpiColors.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(HerpiColors.DarkGreenMain)
                        .clickable {
                            isMapExpanded = true
                            viewModel.analytics.logEvent(
                                Const.Event.EXPAND_DISTRIBUTION_MAP,
                                mapOf("specie_id" to state.data?.id)
                            )
                        }
                        .padding(vertical = 16.dp)
                )
                VerticalMargin(size = 24.dp)
            }

            // Top Bar Controls
            TopBarControls(
                onBack = { onBackClick() },
                onShare = {
                    IntentUtil.shareUrl(
                        "https://herpi.ge/${
                            runBlocking { viewModel.appLanguage.firstOrNull() } ?: "ka"
                        }/view/${state.data?.id}/",
                        (context as Activity)
                    )
                    viewModel.analytics.logEvent(
                        Const.Event.SHARE,
                        mapOf("specie_id" to state.data?.id)
                    )
                }
            )
        }
    }

    state.error?.let {
        ErrorBottomSheet(
            title = it.toString(),
            message = it.toString(),
            onDismiss = { viewModel.onEvent(ReptileDetailsEvent.ClearErrors) },
            positiveButton = { },
            negativeButton = { }
        )
    }

    if (isImageViewer) {
        FullScreenImageViewer(
            images = state.data?.gallery?.map { it.url }.orEmpty(),
            defaultPageIndex = currentOpenedImageIndex,
            additionalSubTexts = state.data?.gallery?.map {
                "(C) " + (if (it.credits.isEmpty()) {
                    it.author?.name ?: "---"
                } else it.credits.joinToString(", "))
            }.orEmpty(),
            onClose = {
                isImageViewer = false
            },
            isZoomable = true
        )
    }

    if (isMapExpanded) {
        ExpandedMapDialog(
            areas = state.distribution,
            onCancel = { isMapExpanded = false },
        )
    }
}