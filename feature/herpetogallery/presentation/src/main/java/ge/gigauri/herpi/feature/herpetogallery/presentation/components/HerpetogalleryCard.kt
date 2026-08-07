package ge.gigauri.herpi.feature.herpetogallery.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gigauri.reptiledb.module.core.domain.model.Author
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.extensions.VenomousLabel
import ge.gigauri.herpi.feature.herpetogallery.domain.model.HerpetogalleryItem
import ge.gigauri.herpi.feature.herpetogallery.presentation.R
import kotlin.random.Random

@Composable
fun HerpetogalleryCard(
    item: HerpetogalleryItem,
    onViewClick: () -> Unit,
    onImageClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    var isAuthorNameVisible by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.82f),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onImageClick() }
        ) {
            // Background Image
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.reptile.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Dark gradient overlay for text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.45f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.85f)
                            )
                        )
                    )
            )

            // Top Row: Author badge & Venom status badge
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Author Pill
                item.author?.let { author ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .clickable(onClick = {
                                isAuthorNameVisible = !isAuthorNameVisible
                            })
                            .background(Color(0xFF1E282A).copy(alpha = 0.85f))
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    ) {
                        AsyncImage(
                            model = author.avatarUrl,
                            contentDescription = author.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.DarkGray)
                        )
                        AnimatedVisibility(
                            visible = !isAuthorNameVisible
                        ) {
                            Row {
                                HorizontalMargin(4.dp)
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                                HorizontalMargin(2.dp)
                            }
                        }
                        AnimatedVisibility(
                            visible = isAuthorNameVisible
                        ) {
                            Row {
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = author.name,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                        }
                    }
                }

                // Venom Status Badge using standard VenomousLabel extension
                AnimatedVisibility(
                    visible = !isAuthorNameVisible,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        item.reptile.VenomousLabel(
                            textSize = 10.sp,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                }
            }

            // Bottom Content: Reptile Names & View Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = item.reptile.name,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.reptile.scientificName,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.9f))
                        .clickable { onViewClick() }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_view),
                            color = Color(0xFF1E282A),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color(0xFF1E282A),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            HerpetogalleryCard(
                onViewClick = {},
                item = HerpetogalleryItem(
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
                        avatarUrl = "https://api.herpi.ge/uploads/team_member_avatar_1776972541908.jpg"
                    )
                )
            )
        }
    }
}
