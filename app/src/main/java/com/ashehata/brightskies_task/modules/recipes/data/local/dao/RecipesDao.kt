package com.ashehata.brightskies_task.modules.recipes.data.local.dao

import androidx.room.*
import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Query("SELECT * FROM favourite_recipes")
    fun getAll(): Flow<List<RecipeDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipeDataModel: RecipeDataModel)

    @Query("DELETE FROM favourite_recipes WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM favourite_recipes")
    suspend fun clearAll()

}