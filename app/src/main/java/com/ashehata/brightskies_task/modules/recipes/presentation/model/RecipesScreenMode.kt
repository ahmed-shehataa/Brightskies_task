package com.ashehata.brightskies_task.modules.recipes.presentation.model

sealed class RecipesScreenMode {
    object All : RecipesScreenMode()
    object FavouriteOnly : RecipesScreenMode()
}
