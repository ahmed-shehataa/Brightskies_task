package com.ashehata.brightskies_task.common.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun AppBar(
    modifier: Modifier = Modifier.padding(
        top = 12.dp,
        end = 20.dp,
        bottom = 12.dp
    ),
    title: String,
    hasBackButton: Boolean = false,
    hasDivider: Boolean = false,
    onBackPressed: () -> Unit = {},
    extraBody: @Composable (ColumnScope) -> Unit = {},
) {

    Row(modifier, verticalAlignment = Alignment.Top) {
        if (hasBackButton)
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

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onSecondary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (hasDivider)
                Divider(color = MaterialTheme.colors.onSecondary.copy(alpha = .2f))


            extraBody(this)
        }
    }

}