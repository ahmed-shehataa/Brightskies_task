package com.ashehata.brightskies_task.modules.recipes.data.repository

import com.ashehata.brightskies_task.modules.recipes.data.local.source.RecipesLocalDataSource
import com.ashehata.brightskies_task.modules.recipes.data.mapper.toDataModel
import com.ashehata.brightskies_task.modules.recipes.data.mapper.toDomainModel
import com.ashehata.brightskies_task.modules.recipes.data.remote.RecipesRemoteDataSource
import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val remote: RecipesRemoteDataSource,
    private val local: RecipesLocalDataSource
) : RecipesRepository {

    override fun getFavouriteRecipes(): Flow<List<RecipeDomainModel>> {
        return local.getAllRecipes().map {
            it.map {
                it.toDomainModel().apply {
                    isFavourite = true
                }
            }
        }
    }

    override suspend fun getAllRecipes(): List<RecipeDomainModel> {
        val favoriteRecipesIds = local.getAllRecipes().firstOrNull()?.map {
            it.id
        }

        return remote.getRecipes().map {
            it.toDomainModel().apply {
                if (favoriteRecipesIds?.contains(it.id) == true) {
                    isFavourite = true
                }
            }
        }
    }

    override suspend fun addRecipeToFavourite(recipeDomainModel: RecipeDomainModel) {
        local.insertRecipe(recipeDomainModel.toDataModel())
    }

    override suspend fun removeRecipeFromFavourite(id: String) {
        local.deleteRecipe(id)
    }

    override suspend fun removeAllRecipeFromFavourite() {
        local.clearAllRecipes()
    }


}