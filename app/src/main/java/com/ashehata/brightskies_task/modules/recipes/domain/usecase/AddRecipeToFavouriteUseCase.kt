package com.ashehata.brightskies_task.modules.recipes.domain.usecase

import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import javax.inject.Inject

class AddRecipeToFavouriteUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend fun execute(recipeDomainModel: RecipeDomainModel) {
        repository.addRecipeToFavourite(recipeDomainModel)
    }
}