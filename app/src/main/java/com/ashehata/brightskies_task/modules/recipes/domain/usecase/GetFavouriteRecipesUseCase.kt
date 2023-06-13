package com.ashehata.brightskies_task.modules.recipes.domain.usecase

import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteRecipesUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend fun execute(): Flow<List<RecipeDomainModel>> {
        return repository.getFavouriteRecipes()
    }
}