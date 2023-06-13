package com.ashehata.brightskies_task.modules.recipes.data.local.source

import com.ashehata.brightskies_task.modules.recipes.data.local.dao.RecipesDao
import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesLocalDataSourceImpl @Inject constructor(
    private val dao: RecipesDao
) : RecipesLocalDataSource {

    override fun getAllRecipes(): Flow<List<RecipeDataModel>> {
        return dao.getAll()
    }

    override suspend fun insertRecipe(recipeDataModel: RecipeDataModel) {
        dao.insert(recipeDataModel)
    }

    override suspend fun deleteRecipe(id: String) {
        dao.delete(id)
    }

    override suspend fun clearAllRecipes() {
        dao.clearAll()
    }
}