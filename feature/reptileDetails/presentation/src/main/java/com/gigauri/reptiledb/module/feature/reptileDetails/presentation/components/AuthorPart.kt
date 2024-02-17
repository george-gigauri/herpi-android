package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gigauri.reptiledb.module.core.domain.model.Author
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark

@Composable
fun AuthorPart(
    author: Author,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.then(modifier)
    ) {
        // Author Name
        Column(
            modifier = Modifier
        ) {
            SecondaryTextLighterDark(text = "ავტორი", modifier = Modifier.align(Alignment.End))
            VerticalMargin(size = 2.dp)
            PrimaryTextDarkGray(text = author.name, modifier = Modifier.align(Alignment.End))
        }
        // Author Avatar
        AsyncImage(
            model = author.avatarUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(HerpiColors.CreamyYellow)
        )
    }
}