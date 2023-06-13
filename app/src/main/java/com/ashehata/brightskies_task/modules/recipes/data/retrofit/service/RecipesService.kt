package com.ashehata.brightskies_task.modules.recipes.data.retrofit.service

import com.ashehata.brightskies_task.common.data.retrofit.ApiPaths
import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import retrofit2.http.GET


interface RecipesService {

    @GET(ApiPaths.RECIPES)
    suspend fun getRecipes(): List<RecipeDataModel>?
}