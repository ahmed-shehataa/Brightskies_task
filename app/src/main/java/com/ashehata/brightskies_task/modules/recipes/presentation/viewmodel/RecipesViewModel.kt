package com.ashehata.brightskies_task.modules.recipes.presentation.viewmodel

import com.ashehata.brightskies_task.base.BaseViewModel
import com.ashehata.brightskies_task.modules.recipes.domain.usecase.*
import com.ashehata.brightskies_task.modules.recipes.presentation.contract.RecipesEvent
import com.ashehata.brightskies_task.modules.recipes.presentation.contract.RecipesState
import com.ashehata.brightskies_task.modules.recipes.presentation.contract.RecipesViewState
import com.ashehata.brightskies_task.modules.recipes.presentation.mapper.toDomainModel
import com.ashehata.brightskies_task.modules.recipes.presentation.mapper.toUIModel
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipesScreenMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val getFavouriteRecipesUseCase: GetFavouriteRecipesUseCase,
    private val addRecipeToFavouriteUseCase: AddRecipeToFavouriteUseCase,
    private val removeRecipeFromFavouriteUseCase: RemoveRecipeFromFavouriteUseCase,
    private val clearAllRecipesFromFavouriteUseCase: ClearAllRecipesFromFavouriteUseCase
) : BaseViewModel<RecipesEvent, RecipesViewState, RecipesState>() {


    init {
        getAllRecipes()
        getFavRecipes()
    }

    private fun getFavRecipes() {
        launchCoroutine {
            getFavouriteRecipesUseCase.execute().collectLatest {
                viewStates?.favRecipes?.clear()
                val list = it.map { it.toUIModel() }
                viewStates?.favRecipes?.addAll(list)
            }
        }
    }

    private fun getAllRecipes() {
        launchCoroutine {
            setLoading()
            viewStates?.allRecipes?.clear()
            viewStates?.allRecipes?.addAll(getAllRecipesUseCase.execute().map { it.toUIModel() })
            setDoneLoading()
        }
    }

    override fun handleEvents(event: RecipesEvent) {
        when (event) {
            is RecipesEvent.AddRecipeToFavourite -> {
                launchCoroutine {
                    addRecipeToFavouriteUseCase.execute(event.recipeDomainModel.toDomainModel())
                    val index = viewStates?.allRecipes?.indexOf(event.recipeDomainModel)
                    if (index != null) {
                        viewStates?.allRecipes?.set(
                            index,
                            event.recipeDomainModel.copy(isFavourite = true)
                        )
                    }
                    setState {
                        RecipesState.AddSuccess
                    }
                }
            }
            is RecipesEvent.ChangeScreenMode -> {
                viewStates?.screenMode?.value = when (viewStates?.screenMode?.value) {
                    RecipesScreenMode.All -> {
                        viewStates?.isNetworkError?.value = false
                        RecipesScreenMode.FavouriteOnly
                    }
                    RecipesScreenMode.FavouriteOnly -> {
                        if (viewStates?.allRecipes.isNullOrEmpty())
                            getAllRecipes()

                        RecipesScreenMode.All
                    }
                    null -> RecipesScreenMode.All
                }
            }
            is RecipesEvent.OnRecipeClicked -> {
                setState {
                    RecipesState.OpenRecipeDetailsScreen(event.recipeDomainModel)
                }
            }
            RecipesEvent.RefreshScreen -> {

            }
            is RecipesEvent.RemoveRecipeFromFavourite -> {
                launchCoroutine {
                    removeRecipeFromFavouriteUseCase.execute(event.recipeDomainModel.id)
                    val currentRecipe = viewStates?.allRecipes?.find {
                        it?.id == event.recipeDomainModel.id
                    }
                    val index = viewStates?.allRecipes?.indexOf(currentRecipe)
                    if (index != null) {
                        viewStates?.allRecipes?.set(
                            index,
                            event.recipeDomainModel.copy(isFavourite = false)
                        )
                    }
                    setState {
                        RecipesState.RemoveSuccess
                    }
                }
            }
            RecipesEvent.ClearAllFavourite -> {
                launchCoroutine {
                    clearAllRecipesFromFavouriteUseCase.execute()
                    val favIndexMapped = mutableMapOf<Int, RecipeUIModel>()

                    viewStates?.allRecipes?.filter {
                        it?.isFavourite ?: false
                    }?.filterNotNull()?.forEach {
                        viewStates?.allRecipes?.indexOf(it)?.let { index ->
                            favIndexMapped.put(index, it.copy(isFavourite = false))

                        }
                    }

                    favIndexMapped.forEach {
                        viewStates?.allRecipes?.set(
                            it.key,
                            it.value
                        )
                    }

                }
            }
        }
    }


    override fun createInitialViewState(): RecipesViewState {
        return RecipesViewState()
    }
}