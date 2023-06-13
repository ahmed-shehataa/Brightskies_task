package com.ashehata.brightskies_task.common.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun AppBarWithActions(
    modifier: Modifier,
    onBackPressed: () -> Unit,
    onSharePressed: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                start = 12.dp,
                end = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    onBackPressed()
                }
                .padding(8.dp)

        )


        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    onSharePressed()
                }
                .padding(8.dp)
        )


    }
}