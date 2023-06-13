package com.ashehata.brightskies_task.modules.recipes.domain.repository

import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    fun getFavouriteRecipes(): Flow<List<RecipeDomainModel>>

    suspend fun getAllRecipes(): List<RecipeDomainModel>

    suspend fun addRecipeToFavourite(recipeDomainModel: RecipeDomainModel)

    suspend fun removeRecipeFromFavourite(id: String)

    suspend fun removeAllRecipeFromFavourite()
}