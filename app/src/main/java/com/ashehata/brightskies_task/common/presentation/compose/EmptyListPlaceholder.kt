package com.ashehata.brightskies_task.common.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.ashehata.brightskies_task.R


@Composable
fun EmptyListPlaceholder(modifier: Modifier) {


   Box(modifier) {
       Column(
           modifier = Modifier.align(Alignment.Center),
           verticalArrangement = Arrangement.spacedBy(8.dp),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {

           Image(
               modifier = Modifier.size(150.dp),
               imageVector = ImageVector.vectorResource(id = R.drawable.empty_placeholder),
               contentDescription = null
           )

           Text(
               text = stringResource(id = R.string.no_recipes_found),
               style = MaterialTheme.typography.body1.copy(
                   color = MaterialTheme.colors.onSecondary
               )
           )


       }
   }
}