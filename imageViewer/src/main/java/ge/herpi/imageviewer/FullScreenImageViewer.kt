package ge.herpi.imageviewer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun FullScreenImageViewer(
    images: List<String?>,
    defaultPageIndex: Int = 0,
    additionalSubTexts: List<String>? = null,
    isZoomable: Boolean = false,
    onClose: () -> Unit = {}
) {

    val pagerState = com.google.accompanist.pager.rememberPagerState(initialPage = 0)
    var currentImageIndex: Int by rememberSaveable {
        mutableIntStateOf(defaultPageIndex)
    }
    var currentImageState: AsyncImagePainter.State by remember {
        mutableStateOf(AsyncImagePainter.State.Empty)
    }

    LaunchedEffect(key1 = Unit, block = {
        pagerState.scrollToPage(defaultPageIndex)
    })

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {

            com.google.accompanist.pager.HorizontalPager(
                count = images.size
            ) { page ->
                currentImageIndex = page

                if (images.isNotEmpty()) {
                    val imageUrl = images[currentImageIndex]
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        onState = {
                            currentImageState = it
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .zoomable(
                                rememberZoomState(),
                                zoomEnabled = isZoomable
                            )
                    )
                }
            }

            /* States */
            // Loading
            if (currentImageState is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator(
                    color = Color.Green,
                    modifier = Modifier
                        .size(72.dp)
                        .align(Alignment.Center)
                )
            }

            // Error
            if (currentImageState is AsyncImagePainter.State.Error) {
                Text(
                    text = "Image could not be loaded :(",
                    color = White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }

            // TopBar Controls
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(Color.Black)
                    .padding(horizontal = 0.dp)
                    .padding(bottom = 8.dp, top = 8.dp)
                    .align(Alignment.TopCenter)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .clickable { onClose() }
                        .padding(12.dp))

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "${currentImageIndex + 1}/${images.size}",
                    color = LightGray,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Bottom Additional MetaData
            if (!additionalSubTexts.isNullOrEmpty()) {
                Text(
                    text = additionalSubTexts[currentImageIndex],
                    color = White,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.Black)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 32.dp, top = 24.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}