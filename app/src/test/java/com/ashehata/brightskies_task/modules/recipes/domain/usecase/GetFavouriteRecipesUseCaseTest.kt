package com.ashehata.brightskies_task.modules.recipes.domain.usecase

import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetFavouriteRecipesUseCaseTest {

    private lateinit var recipesRepository: RecipesRepository
    private lateinit var recipes: List<RecipeDomainModel>
    private lateinit var getFavouriteRecipesUseCase: GetFavouriteRecipesUseCase

    @Before
    fun setUp() {
        recipes = listOf(
            RecipeDomainModel(
                id = "0",
                name = "A"
            ),
            RecipeDomainModel(
                id = "1",
                name = "B"
            ),
        )
        recipesRepository = Mockito.mock()
        getFavouriteRecipesUseCase = GetFavouriteRecipesUseCase(recipesRepository)
    }

    @Test
    fun `execute() GetFavouriteRecipesUseCase`() = runBlocking {
        val flowList = flow {
            emit(recipes)
        }

        Mockito.`when`(recipesRepository.getFavouriteRecipes()).thenReturn(flowList)

        getFavouriteRecipesUseCase.execute().collectLatest {
            Assert.assertEquals(recipes, it)
            Assert.assertEquals(recipes.size, it.size)
        }
    }

}