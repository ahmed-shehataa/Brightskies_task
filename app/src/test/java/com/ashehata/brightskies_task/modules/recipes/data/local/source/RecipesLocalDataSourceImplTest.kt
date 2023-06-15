package com.ashehata.brightskies_task.modules.recipes.data.local.source

import com.ashehata.brightskies_task.modules.recipes.data.local.dao.RecipesDao
import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RecipesLocalDataSourceImplTest {

    private lateinit var recipesDao: RecipesDao
    private lateinit var recipesLocalDataSourceImpl: RecipesLocalDataSourceImpl

    @Before
    fun setUp() {
        recipesDao = mock()
        recipesLocalDataSourceImpl = RecipesLocalDataSourceImpl(recipesDao)
    }

    @Test
    fun `getAll()`() = runBlocking {
        val recipesList = flow {
            emit(
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
            )
        }

        `when`(recipesDao.getAll()).thenReturn(recipesList)
        val actual = recipesLocalDataSourceImpl.getAllRecipes()

        Assert.assertEquals(recipesList, actual)
    }

    @Test
    fun `clearAll()`() = runBlocking {
        val recipesList = flow<List<RecipeDataModel>> {
            emit(
                emptyList()
            )
        }

        `when`(recipesDao.getAll()).thenReturn(recipesList)
        recipesLocalDataSourceImpl.clearAllRecipes()

        recipesList.collectLatest {
            Assert.assertEquals(0, it.size)
        }

    }

    @Test
    fun `insert new Recipe()`() = runBlocking {
        val recipe = RecipeDataModel(
            id = "0",
            name = "ahmed"
        )
        val recipesList = flow {
            emit(listOf(recipe))
        }

        `when`(recipesDao.getAll()).thenReturn(recipesList)
        recipesLocalDataSourceImpl.insertRecipe(recipe)

        recipesList.collectLatest {
            Assert.assertEquals(1, it.size)
        }

    }

    @Test
    fun `insert duplicate Recipe()`() = runBlocking {
        val recipe = RecipeDataModel(
            id = "0",
            name = "ahmed"
        )
        val recipesMutableList = mutableListOf(recipe)


        val recipesList = flow {
            emit(recipesMutableList)
        }

        `when`(recipesDao.insert(recipe)).then {
            recipesMutableList.set(0, recipe)
        }
        `when`(recipesDao.getAll()).thenReturn(recipesList)
        recipesLocalDataSourceImpl.insertRecipe(recipe)

        recipesList.collectLatest {
            Assert.assertEquals(1, it.size)
        }

    }


}