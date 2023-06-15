package com.ashehata.brightskies_task.modules.recipes.data.remote

import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import com.ashehata.brightskies_task.modules.recipes.data.retrofit.service.RecipesService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RecipesRemoteDataSourceImplTest {

    private lateinit var service: RecipesService
    private lateinit var recipesRemoteDataSource: RecipesRemoteDataSource

    @Before
    fun setUp() {
        service = Mockito.mock()
        recipesRemoteDataSource = RecipesRemoteDataSourceImpl(service)
    }


    @Test
    fun `getAll()`() = runBlocking {
        val recipesList =
            listOf(
                RecipeDataModel(
                    id = "0",
                    name = "ahmed"
                ),
                RecipeDataModel(
                    id = "1",
                    name = "mo"
                ),
                RecipeDataModel(
                    id = "2",
                    name = "ali"
                )
            )



        Mockito.`when`(service.getRecipes()).thenReturn(recipesList)
        val actual = recipesRemoteDataSource.getRecipes()

        Assert.assertEquals(recipesList.size, actual.size)
    }

}