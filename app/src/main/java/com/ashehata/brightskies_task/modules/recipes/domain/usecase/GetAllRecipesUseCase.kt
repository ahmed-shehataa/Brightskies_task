package com.ashehata.brightskies_task.modules.recipes.domain.usecase

import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend fun execute(): List<RecipeDomainModel> {
        return repository.getAllRecipes()
    }
}