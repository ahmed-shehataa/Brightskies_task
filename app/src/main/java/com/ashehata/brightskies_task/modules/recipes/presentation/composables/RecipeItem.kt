package com.ashehata.brightskies_task.modules.recipes.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel

@Composable
fun RecipeItem(
    recipe: RecipeUIModel?,
    onRecipeClicked: (RecipeUIModel) -> Unit,
    onAddRecipeToFavourite: (RecipeUIModel) -> Unit,
    onRemoveRecipeFromFavourite: (RecipeUIModel) -> Unit,
) {

    val isFav = remember(recipe) {
        recipe?.isFavourite ?: false
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .border(color = Color.Gray, shape = RoundedCornerShape(8.dp), width = 1.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                recipe?.let { onRecipeClicked(it) }
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe?.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = recipe?.name ?: "",
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSecondary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = recipe?.description ?: "",
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSecondary
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    if (isFav) {
                        recipe?.let { onRemoveRecipeFromFavourite(it) }
                    } else {
                        recipe?.let { onAddRecipeToFavourite(it) }

                    }
                }
                .padding(4.dp),
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Favorite_recipe",
            tint = if (recipe?.isFavourite == true) Color.Red else Color.Black
        )

    }

}