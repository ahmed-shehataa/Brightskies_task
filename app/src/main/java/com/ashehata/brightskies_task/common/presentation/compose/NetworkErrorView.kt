package com.ashehata.brightskies_task.common.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ashehata.brightskies_task.R


@Composable
fun NetworkErrorView(onRetry: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.network_error),
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onSecondary
                )
            )

            Button(onClick = onRetry) {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.White
                    )
                )
            }

        }

    }
}