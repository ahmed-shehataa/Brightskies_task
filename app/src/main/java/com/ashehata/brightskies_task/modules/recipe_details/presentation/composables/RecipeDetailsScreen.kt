package com.ashehata.brightskies_task.modules.recipe_details.presentation.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.ashehata.brightskies_task.common.presentation.GeneralObservers
import com.ashehata.brightskies_task.modules.recipes.presentation.contract.RecipesEvent
import com.ashehata.brightskies_task.modules.recipes.presentation.contract.RecipesState
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel
import com.ashehata.brightskies_task.modules.recipes.presentation.viewmodel.RecipesViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun RecipeDetailsScreen(
    recipe: RecipeUIModel,
    navigator: DestinationsNavigator,
    viewModel: RecipesViewModel
) {
    val recipeState = remember(recipe) {
        mutableStateOf(recipe)
    }

    val context = LocalContext.current

    val onAddRecipeToFavourite: (RecipeUIModel) -> Unit = remember {
        {
            viewModel.setEvent(RecipesEvent.AddRecipeToFavourite(it))
        }
    }

    val onRemoveRecipeFromFavourite: (RecipeUIModel) -> Unit = remember {
        {
            viewModel.setEvent(RecipesEvent.RemoveRecipeFromFavourite(it))
        }
    }


    RecipeDetailsScreenContent(
        recipe = recipeState,
        onAddRecipeToFavourite = onAddRecipeToFavourite,
        onRemoveRecipeFromFavourite = onRemoveRecipeFromFavourite
    ) {
        navigator.popBackStack()
    }

    GeneralObservers<RecipesState, RecipesViewModel>(viewModel = viewModel) {
        when (it) {
            RecipesState.AddSuccess -> {
                Toast.makeText(context, "Add to Favourite!", Toast.LENGTH_SHORT).show()
            }
            RecipesState.RemoveSuccess -> {
                Toast.makeText(context, "Removed from Favourite!", Toast.LENGTH_SHORT).show()

            }
            else -> {

            }
        }
    }

}


