package com.ashehata.brightskies_task.recipes.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ashehata.brightskies_task.database.room.AppDatabase
import com.ashehata.brightskies_task.modules.recipes.data.local.dao.RecipesDao
import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class RecipesDaoTest {

    private lateinit var recipesDao: RecipesDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        recipesDao = appDatabase.recipesDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun when_insertNewRecipe_returns_one_recipe() = runBlocking {
        val recipe = RecipeDataModel(
            id = "0",
            name = "ahmed",
        )

        recipesDao.insert(recipe)

        Assert.assertEquals(1, recipesDao.getAll().first().size)
    }

    @Test
    fun when_insertDuplicateRecipes_returns_one_recipe() = runBlocking {
        val recipeList = List(2) {
            RecipeDataModel(
                id = "1",
                name = "Recipe",
            )
        }

        recipeList.forEach {
            recipesDao.insert(it)
        }

        Assert.assertEquals(1, recipesDao.getAll().first().size)
    }

    @Test
    fun when_insertDifferentRecipes_returns_two_recipe() = runBlocking {
        val recipe = RecipeDataModel(
            id = "0",
            name = "ahmed",
        )

        val recipeTwo = RecipeDataModel(
            id = "1",
            name = "mo",
        )

        recipesDao.insert(recipe)
        recipesDao.insert(recipeTwo)

        Assert.assertEquals(2, recipesDao.getAll().first().size)
    }

    @Test
    fun when_insertRecipesList_returns_list_recipe() = runBlocking {
        val recipeList = List(20) {
            RecipeDataModel(
                id = it.toString(),
                name = "Recipe",
            )
        }

        recipeList.forEach {
            recipesDao.insert(it)
        }

        Assert.assertEquals(recipeList.size, recipesDao.getAll().first().size)
    }

    @Test
    fun when_clearAll_returns_no_list() = runBlocking {
        val recipeList = List(20) {
            RecipeDataModel(
                id = it.toString(),
                name = "Recipe",
            )
        }

        recipeList.forEach {
            recipesDao.insert(it)
        }

        recipesDao.clearAll()

        Assert.assertEquals(0, recipesDao.getAll().first().size)
    }


    @Test
    fun when_deleteByID_returns_no_item() = runBlocking {
        val recipe = RecipeDataModel(
            id = "0",
            name = "ahmed",
        )

        recipesDao.insert(recipe)

        recipesDao.delete(recipe.id)

        val isContainsRecipe = recipesDao.getAll().first().contains(recipe)

        Assert.assertEquals(0, recipesDao.getAll().first().size)
        Assert.assertEquals(false, isContainsRecipe)
    }


}