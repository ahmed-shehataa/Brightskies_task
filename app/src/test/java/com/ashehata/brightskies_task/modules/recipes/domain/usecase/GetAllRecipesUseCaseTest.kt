package com.ashehata.brightskies_task.modules.recipes.domain.usecase

import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetAllRecipesUseCaseTest {

    private lateinit var recipesRepository: RecipesRepository
    private lateinit var recipes: List<RecipeDomainModel>
    private lateinit var getAllRecipesUseCase: GetAllRecipesUseCase

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
        getAllRecipesUseCase = GetAllRecipesUseCase(recipesRepository)
    }

    @Test
    fun `execute() GetAllRecipesUseCase`() = runBlocking {
        Mockito.`when`(recipesRepository.getAllRecipes()).thenReturn(recipes)

        val actual = getAllRecipesUseCase.execute()

        assertEquals(recipes, actual)
        assertEquals(recipes.size, actual.size)
    }
}