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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
            .statusBarsPadding()
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

            item {
                VerticalMargin(24.dp)
            }

            // Person List
            item { VerticalMargin(size = 8.dp) }
            items(state.data) {
                Column {
                    PrimaryTextDarkGray(
                        text = it.category,
                        color = HerpiColors.DarkGreenMain,
                        weight = FontWeight.Bold
                    )
                    VerticalMargin(size = 12.dp)

                    it.data.map { member ->
                        TeamMember(
                            context = context,
                            data = member,
                            isExpanded = member.id == state.expandedItemId,
                            onEmailClick = {
                                Intent(Intent.ACTION_SENDTO).apply {
                                    data = "mailto:${member.email}".toUri()
                                    putExtra(Intent.EXTRA_EMAIL, member.email)
                                }.let { intent ->
                                    context.startActivity(intent)
                                }

                                viewModel.analytics.logEvent(
                                    Const.Event.CLICK_TEAM_EMAIL, mapOf("member" to member.email)
                                )
                            },
                            onClick = {
                                viewModel.onEvent(TeamUiEvent.Expand(member.id))
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
                                    mapOf("member" to member.email, "network" to social.networkName)
                                )
                            },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}