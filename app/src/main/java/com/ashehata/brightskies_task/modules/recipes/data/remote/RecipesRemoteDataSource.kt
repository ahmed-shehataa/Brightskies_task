package com.ashehata.brightskies_task.modules.recipes.data.remote

import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel

interface RecipesRemoteDataSource {

    suspend fun getRecipes(): List<RecipeDataModel>
}