package com.gigauri.reptiledb.module.feature.team.presentation

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.common.util.IntentUtil
import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.feature.team.presentation.components.TeamMember
import com.gigauri.reptiledb.module.feature.team.presentation.components.TopBar

@Composable
fun TeamScreen(
    onDrawerClick: () -> Unit,
    onChatClick: () -> Unit,
) {
    val context = LocalContext.current
    val viewModel: TeamViewModel = hiltViewModel()
    val state: TeamUiState by viewModel.state.collectAsStateWithLifecycle()
    val error: ErrorType? by viewModel.error.collectAsStateWithLifecycle(initialValue = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HerpiColors.DarkGreenMain)
    ) {
        TopBar(
            onDrawerClick = onDrawerClick,
            onChatClick = onChatClick
        )

        // White Container
        LazyColumn(
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(HerpiColors.LightWindowBg)
        ) {

            // Title Team
            item {
                PrimaryTextDarkGray(text = stringResource(id = R.string.title_team))
            }

            // Person List
            item { VerticalMargin(size = 8.dp) }
            items(state.data) {
                TeamMember(
                    context = context,
                    data = it,
                    isExpanded = it.id == state.expandedItemId,
                    onEmailClick = {
                        Intent(Intent.ACTION_SENDTO).apply {
                            data = "mailto:${it.email}".toUri()
                            putExtra(Intent.EXTRA_EMAIL, it.email)
                        }.let { intent ->
                            context.startActivity(intent)
                        }

                        viewModel.analytics.logEvent(
                            Const.Event.CLICK_TEAM_EMAIL, mapOf("member" to it.email)
                        )
                    },
                    onClick = {
                        viewModel.onEvent(TeamUiEvent.Expand(it.id))
                    },
                    onSocialClick = { social ->
                        (context as? Activity)?.let { activity ->
                            IntentUtil.openUrlToBrowserPopup(
                                activity,
                                social.profileUrl
                            )
                        }

                        viewModel.analytics.logEvent(
                            Const.Event.CLICK_TEAM_SOCIAL,
                            mapOf("member" to it.email, "network" to social.networkName)
                        )
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}