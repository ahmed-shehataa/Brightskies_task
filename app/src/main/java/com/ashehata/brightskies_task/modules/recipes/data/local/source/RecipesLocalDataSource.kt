package com.ashehata.brightskies_task.modules.recipes.data.local.source

import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataSource {

    fun getAllRecipes(): Flow<List<RecipeDataModel>>

    suspend fun insertRecipe(recipeDataModel: RecipeDataModel)

    suspend fun deleteRecipe(id: String)

    suspend fun clearAllRecipes()

}