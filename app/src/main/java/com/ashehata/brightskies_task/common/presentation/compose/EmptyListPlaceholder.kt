package com.ashehata.brightskies_task.common.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ashehata.brightskies_task.R


@Composable
fun EmptyListPlaceholder(modifier: Modifier) {


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.no_recipes_found),
            style = MaterialTheme.typography.subtitle1.copy(
                color = MaterialTheme.colors.onSecondary
            )
        )


    }
}