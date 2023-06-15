package com.ashehata.brightskies_task.modules.recipes.data.repository

import com.ashehata.brightskies_task.modules.recipes.data.local.source.RecipesLocalDataSource
import com.ashehata.brightskies_task.modules.recipes.data.mapper.toDomainModel
import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import com.ashehata.brightskies_task.modules.recipes.data.remote.RecipesRemoteDataSource
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.kotlin.times

class RecipesRepositoryImplTest {

    private lateinit var remote: RecipesRemoteDataSource
    private lateinit var local: RecipesLocalDataSource
    private lateinit var recipesRepository: RecipesRepository
    private lateinit var localRecipes: List<RecipeDataModel>
    private lateinit var remoteRecipes: List<RecipeDataModel>


    @Before
    fun setUp() {
        remote = Mockito.mock()
        local = Mockito.mock()
        localRecipes = listOf(
            RecipeDataModel(
                id = "0",
                name = "A"
            ),
            RecipeDataModel(
                id = "1",
                name = "B"
            ),
        )
        remoteRecipes = listOf(
            RecipeDataModel(
                id = "0",
                name = "A"
            ),
            RecipeDataModel(
                id = "1",
                name = "B"
            ),
            RecipeDataModel(
                id = "2",
                name = "C"
            )
        )
        recipesRepository = RecipesRepositoryImpl(remote, local)
    }

    @After
    fun tearDown() {
        localRecipes = emptyList()
        remoteRecipes = emptyList()
    }

    @Test
    fun `getFavouriteRecipes()`() = runBlocking {
        val flowList = flow {
            emit(localRecipes)
        }

        Mockito.`when`(local.getAllRecipes()).thenReturn(flowList)
        val actual = recipesRepository.getFavouriteRecipes()

        actual.collectLatest {
            Assert.assertEquals(localRecipes.size, it.size)

        }
    }


    @Test
    fun `getAllRecipes()`() = runBlocking {
        val flowList = flow {
            emit(localRecipes)
        }

        Mockito.`when`(local.getAllRecipes()).thenReturn(flowList)
        Mockito.`when`(remote.getRecipes()).thenReturn(remoteRecipes)
        val actual = recipesRepository.getAllRecipes()

        Assert.assertEquals(remoteRecipes.size, actual.size)
        Assert.assertEquals(true, actual.containsAll(localRecipes.map {
            it.toDomainModel().copy(
                isFavourite = true
            )
        }))

    }

    @Test
    fun `addRecipeToFavourite()`() = runBlocking {
        val newRecipe = RecipeDataModel(
            id = "100",
            name = "XYZ"
        )

        recipesRepository.addRecipeToFavourite(newRecipe.toDomainModel())

        verify(local, times(1)).insertRecipe(newRecipe)
    }


    @Test
    fun `removeRecipeFromFavourite()`() = runBlocking {
        val recipeID = "101"

        recipesRepository.removeRecipeFromFavourite(recipeID)

        verify(local, times(1)).deleteRecipe(recipeID)
    }

    @Test
    fun `removeAllRecipeFromFavourite()`() = runBlocking {
        recipesRepository.removeAllRecipeFromFavourite()
        verify(local, times(1)).clearAllRecipes()
    }


}