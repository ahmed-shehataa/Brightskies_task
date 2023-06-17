package com.ashehata.brightskies_task.modules.recipes.data.remote

import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import com.ashehata.brightskies_task.modules.recipes.data.retrofit.service.RecipesService
import javax.inject.Inject

class RecipesRemoteDataSourceImpl @Inject constructor(
    private val service: RecipesService
) : RecipesRemoteDataSource {

    override suspend fun getRecipes(): List<RecipeDataModel> {
        return service.getRecipes() ?: emptyList()
    }
}