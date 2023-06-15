package com.ashehata.brightskies_task.modules.recipes.domain.usecase

import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times

class AddRecipeToFavouriteUseCaseTest {

    private lateinit var recipesRepository: RecipesRepository
    private lateinit var addRecipeToFavouriteUseCase: AddRecipeToFavouriteUseCase

    @Before
    fun setUp() {
        recipesRepository = Mockito.mock()
        addRecipeToFavouriteUseCase = AddRecipeToFavouriteUseCase(recipesRepository)
    }

    @Test
    fun `execute() GetAllRecipesUseCase`() = runBlocking {
        val recipe = RecipeDomainModel(
            id = "0",
            name = "A"
        )

        addRecipeToFavouriteUseCase.execute(recipe)

        Mockito.verify(recipesRepository, times(1)).addRecipeToFavourite(recipe)
    }
}