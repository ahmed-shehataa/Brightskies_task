package com.ashehata.brightskies_task.modules.recipes.data.mapper

import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel
import com.ashehata.brightskies_task.modules.recipes.domain.model.RecipeDomainModel

fun RecipeDomainModel.toDataModel() = RecipeDataModel(
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
    weeks = weeks
)
