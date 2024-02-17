package com.gigauri.reptiledb.module.feature.search.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.feature.search.presentation.R

@Composable
fun SearchEditText(
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var text: String by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit, block = {
        focusRequester.requestFocus()
    })

    TextField(
        value = text,
        shape = RoundedCornerShape(100),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedContainerColor = HerpiColors.White,
            unfocusedContainerColor = HerpiColors.White,
            disabledContainerColor = HerpiColors.White,
            errorContainerColor = HerpiColors.White,
        ),
        textStyle = TextStyle(
            fontSize = 15.sp,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
        ),
        placeholder = {
            if (text.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.hint_search_here),
                    color = HerpiColors.LightGray,
                    fontSize = 15.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        },
        singleLine = true,
        onValueChange = {
            text = it
            onTextChange(it)
        },
        modifier = Modifier
            .height(48.dp)
            .focusRequester(focusRequester)
            .then(modifier)
    )
}