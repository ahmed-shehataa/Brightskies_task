package com.ashehata.brightskies_task.modules.recipes.presentation.mapper

import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel

fun RecipeDomainModel.toUIModel() = RecipeUIModel(
    id = id,
    calories = calories,
    carbos = carbos,
    country = country,
    deliverableIngredients = deliverableIngredients,
    description = description,
    difficulty = difficulty,
    fats = fats,
    favorites = favorites,
    fibers = fibers,
    headline = headline,
    highlighted = highlighted,
    image = image,
    incompatibilities = incompatibilities,
    ingredients = ingredients,
    keywords = keywords,
    name = name,
    products = products,
    proteins = proteins,
    rating = rating,
    ratings = ratings,
    time = time,
    undeliverableIngredients = undeliverableIngredients,
    weeks = weeks,
    isFavourite = isFavourite
)


fun RecipeUIModel.toDomainModel() = RecipeDomainModel(
    id = id,
    calories = calories,
    carbos = carbos,
    country = country,
    deliverableIngredients = deliverableIngredients,
    description = description,
    difficulty = difficulty,
    fats = fats,
    favorites = favorites,
    fibers = fibers,
    headline = headline,
    highlighted = highlighted,
    image = image,
    incompatibilities = incompatibilities,
    ingredients = ingredients,
    keywords = keywords,
    name = name,
    products = products,
    proteins = proteins,
    rating = rating,
    ratings = ratings,
    time = time,
    undeliverableIngredients = undeliverableIngredients,
    weeks = weeks,
    isFavourite = isFavourite
)
