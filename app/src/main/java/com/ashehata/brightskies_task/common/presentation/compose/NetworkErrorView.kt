package com.ashehata.brightskies_task.common.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.ashehata.brightskies_task.R


@Composable
fun NetworkErrorView(onRetry: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize().testTag("NetworkErrorView")) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier.size(150.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.network_placeholder),
                contentDescription = null
            )

            Text(
                text = stringResource(id = R.string.network_error),
                style = MaterialTheme.typography.body1.copy(
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