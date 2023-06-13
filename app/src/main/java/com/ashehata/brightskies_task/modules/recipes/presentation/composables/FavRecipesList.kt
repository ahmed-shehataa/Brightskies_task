package com.ashehata.brightskies_task.modules.recipes.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ashehata.brightskies_task.common.presentation.compose.EmptyListPlaceholder
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel

@Composable
fun FavRecipesList(
    listState: LazyListState,
    allRecipes: List<RecipeUIModel?>,
    onRecipeClicked: (RecipeUIModel) -> Unit,
    onAddRecipeToFavourite: (RecipeUIModel) -> Unit,
    onRemoveRecipeFromFavourite: (RecipeUIModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = listState
    ) {

        if (allRecipes.isEmpty()) {

            item {
                EmptyListPlaceholder(modifier = Modifier.fillParentMaxSize())
            }

        } else {
            items(allRecipes) {
                RecipeItem(
                    recipe = it,
                    onRecipeClicked = onRecipeClicked,
                    onAddRecipeToFavourite = onAddRecipeToFavourite,
                    onRemoveRecipeFromFavourite = onRemoveRecipeFromFavourite
                )
            }
        }


    }
}