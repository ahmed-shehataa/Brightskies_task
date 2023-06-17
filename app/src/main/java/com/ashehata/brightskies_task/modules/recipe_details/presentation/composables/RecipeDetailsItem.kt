package com.ashehata.brightskies_task.modules.recipe_details.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun RecipeDetailsItem(
    @StringRes title: Int,
    description: String
) {

    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.subtitle1.copy(
                color = Color.Black
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = description,
            style = MaterialTheme.typography.body1.copy(
                color = Color.Gray
            ),
            maxLines = 20,
            overflow = TextOverflow.Ellipsis
        )
    }


}