package ge.gigauri.herpi.feature.herpetogallery.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gigauri.reptiledb.module.core.domain.model.Author
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import ge.gigauri.herpi.feature.herpetogallery.domain.model.HerpetogalleryItem
import ge.gigauri.herpi.feature.herpetogallery.presentation.R
import ge.gigauri.herpi.feature.herpetogallery.presentation.components.HerpetogalleryCard
import ge.gigauri.herpi.feature.herpetogallery.presentation.viewmodel.HerpetogalleryViewModel
import ge.herpi.imageviewer.FullScreenImageViewer
import kotlinx.coroutines.flow.flowOf
import kotlin.random.Random

@Composable
fun HerpetogalleryScreen(
    onBackClick: () -> Unit,
    onReptileClick: (Reptile) -> Unit,
    viewModel: HerpetogalleryViewModel = hiltViewModel()
) {
    val pagingItems = viewModel.galleryItems.collectAsLazyPagingItems()
    Content(
        onBackClick = onBackClick,
        onReptileClick = onReptileClick,
        pagingItems = pagingItems
    )
}

@Composable
private fun Content(
    onBackClick: () -> Unit,
    onReptileClick: (Reptile) -> Unit,
    pagingItems: LazyPagingItems<HerpetogalleryItem>,
) {
    var isImageViewerOpen by rememberSaveable { mutableStateOf(false) }
    var selectedImageIndex by rememberSaveable { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HerpiColors.DarkGreenMain)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = HerpiColors.DarkGreenMain
                    )
                }
                Text(
                    text = stringResource(id = R.string.title_herpetogallery),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // White rounded container
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(HerpiColors.LightWindowBg)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        count = pagingItems.itemCount,
                        key = { index -> pagingItems[index]?.id ?: index }
                    ) { index ->
                        pagingItems[index]?.let { item ->
                            HerpetogalleryCard(
                                item = item,
                                onViewClick = { onReptileClick(item.reptile) },
                                onImageClick = {
                                    selectedImageIndex = index
                                    isImageViewerOpen = true
                                }
                            )
                        }
                    }

                    if (pagingItems.loadState.append is LoadState.Loading) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = HerpiColors.DarkGreenMain)
                            }
                        }
                    }
                }

                if (pagingItems.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                        color = HerpiColors.DarkGreenMain,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

    if (isImageViewerOpen && pagingItems.itemCount > 0) {
        val allItems = (0 until pagingItems.itemCount).mapNotNull { pagingItems[it] }
        FullScreenImageViewer(
            images = allItems.map { it.imageUrl },
            defaultPageIndex = selectedImageIndex.coerceAtMost(allItems.size - 1),
            additionalSubTexts = allItems.map { item ->
                val creditsStr = if (item.credits.isEmpty()) {
                    item.author?.name ?: "---"
                } else {
                    item.credits.joinToString(", ")
                }
                "${item.reptile.name} (${item.reptile.scientificName})\n(C) $creditsStr"
            },
            onClose = { isImageViewerOpen = false },
            isZoomable = true
        )
    }
}

@Composable
@Preview
private fun Preview() {
    Content(
        onBackClick = {},
        onReptileClick = {},
        pagingItems = flowOf(
            PagingData.from(
                listOf(
                    HerpetogalleryItem(
                        id = Random.nextLong(),
                        reptile = Reptile(
                            id = Random.nextLong(),
                            addedBy = null,
                            family = null,
                            image = null,
                            transparentImage = null,
                            scientificName = "Natrix Natrix",
                            name = "Grass Snake",
                            type = "SNAKE",
                            venomous = false,
                            hasMildVenom = false,
                            hasRedFlag = false
                        ),
                        imageId = Random.nextLong(),
                        imageUrl = "https://herpi.ge/lizard.png",
                        credits = emptyList(),
                        author = Author(
                            id = Random.nextLong(),
                            name = "Giorgi Gigauri",
                            avatarUrl = ""
                        )
                    )
                )
            )
        ).collectAsLazyPagingItems()
    )
}
