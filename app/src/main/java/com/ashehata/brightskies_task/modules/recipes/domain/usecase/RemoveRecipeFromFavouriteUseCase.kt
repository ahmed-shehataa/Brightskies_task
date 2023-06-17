package com.ashehata.brightskies_task.modules.recipes.domain.usecase

import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import javax.inject.Inject

class RemoveRecipeFromFavouriteUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend fun execute(id: String) {
        repository.removeRecipeFromFavourite(id)
    }
}