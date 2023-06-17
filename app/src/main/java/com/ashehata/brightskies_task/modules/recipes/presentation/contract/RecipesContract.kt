package com.ashehata.brightskies_task.modules.recipes.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ashehata.brightskies_task.base.BaseEvent
import com.ashehata.brightskies_task.base.BaseState
import com.ashehata.brightskies_task.base.BaseViewState
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipesScreenMode
import com.ashehata.brightskies_task.modules.user.presentaion.model.UserUIModel

sealed class RecipesEvent : BaseEvent {
    data class OnRecipeClicked(val recipeDomainModel: RecipeUIModel) : RecipesEvent()
    data class AddRecipeToFavourite(val recipeDomainModel: RecipeUIModel) : RecipesEvent()
    data class RemoveRecipeFromFavourite(val recipeDomainModel: RecipeUIModel) : RecipesEvent()
    object OnLogoutClicked : RecipesEvent()
    object ChangeScreenMode : RecipesEvent()
    object ClearAllFavourite : RecipesEvent()
    object RefreshScreen : RecipesEvent()
}

sealed class RecipesState : BaseState {
    data class OpenRecipeDetailsScreen(val recipeDomainModel: RecipeUIModel) : RecipesState()
    object AddSuccess : RecipesState()
    object RemoveSuccess : RecipesState()
    object OpenLoginScreen : RecipesState()

}

data class RecipesViewState(
    override val isNetworkError: MutableState<Boolean> = mutableStateOf(false),
    override val isRefreshing: MutableState<Boolean> = mutableStateOf(false),
    override val isLoading: MutableState<Boolean> = mutableStateOf(false),
    val user: MutableState<UserUIModel?> = mutableStateOf(null),
    val logoutDialogState: MutableState<Boolean> = mutableStateOf(false),
    val deleteAllRecipesDialogState: MutableState<Boolean> = mutableStateOf(false),
    val screenMode: MutableState<RecipesScreenMode> = mutableStateOf(RecipesScreenMode.All),
    val favRecipes: MutableList<RecipeUIModel?> = SnapshotStateList(),
    val allRecipes: MutableList<RecipeUIModel?> = SnapshotStateList(),
) : BaseViewState
