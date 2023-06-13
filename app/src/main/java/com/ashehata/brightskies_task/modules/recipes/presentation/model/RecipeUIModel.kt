package com.ashehata.brightskies_task.modules.recipes.presentation.model

data class RecipeUIModel(
    val id: String,
    val calories: String? = null,
    val carbos: String? = null,
    val country: String? = null,
    val deliverableIngredients: List<String?>? = null,
    val description: String? = null,
    val difficulty: Int? = null,
    val fats: String? = null,
    val favorites: Int? = null,
    val fibers: String? = null,
    val headline: String? = null,
    val highlighted: Boolean? = null,
    val image: String? = null,
    val incompatibilities: String? = null,
    val ingredients: List<String?>? = null,
    val keywords: List<String?>? = null,
    val name: String? = null,
    val products: List<String?>? = null,
    val proteins: String? = null,
    val rating: Double? = null,
    val ratings: Double? = null,
    val time: String? = null,
    val undeliverableIngredients: List<String?>? = null,
    val weeks: List<String?>? = null,
    var isFavourite: Boolean = false
)