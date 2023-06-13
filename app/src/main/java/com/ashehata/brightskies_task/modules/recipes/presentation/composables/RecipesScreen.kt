package com.ashehata.brightskies_task.modules.recipes.presentation.composables

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashehata.brightskies_task.modules.recipes.presentation.contract.RecipesEvent
import com.ashehata.brightskies_task.modules.recipes.presentation.contract.RecipesViewState
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipesScreenMode
import com.ashehata.brightskies_task.modules.recipes.presentation.viewmodel.RecipeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination(start = true)
fun RecipesScreen(
    navigator: DestinationsNavigator,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    val viewStates = remember {
        viewModel.viewStates ?: RecipesViewState()
    }

    val allRecipes = remember {
        viewStates.allRecipes
    }

    val favRecipes = remember {
        viewStates.favRecipes
    }

    val isLoading = remember {
        viewStates.isLoading
    }

    val isRefreshing = remember {
        viewStates.isRefreshing
    }

    val screenMode = remember {
        viewStates.screenMode
    }

    val isNetworkError = remember {
        viewStates.isNetworkError
    }

    val onRecipeClicked: (RecipeUIModel) -> Unit = remember {
        {
            viewModel.setEvent(RecipesEvent.OnRecipeClicked(it))
        }
    }

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

    val onClearAllRecipeFromFavourite = remember {
        {
            viewModel.setEvent(RecipesEvent.ClearAllFavourite)
        }
    }

    val onChangeScreenMode = remember {
        {
            viewModel.setEvent(RecipesEvent.ChangeScreenMode)
        }
    }

    val onRefresh = remember {
        {
            viewModel.setEvent(RecipesEvent.RefreshScreen)
        }
    }

    BackHandler(screenMode.value == RecipesScreenMode.FavouriteOnly) {
        onChangeScreenMode()
    }

    RecipesScreenContent(
        allRecipes = allRecipes,
        favRecipes = favRecipes,
        isLoading = isLoading.value,
        isRefreshing = isRefreshing.value,
        isNetworkError = isNetworkError.value,
        onRecipeClicked = onRecipeClicked,
        onAddRecipeToFavourite = onAddRecipeToFavourite,
        onChangeScreenMode = onChangeScreenMode,
        onClearAllRecipeFromFavourite = onClearAllRecipeFromFavourite,
        onRemoveRecipeFromFavourite = onRemoveRecipeFromFavourite,
        screenMode = screenMode.value,
        onRefresh = onRefresh
    )

}