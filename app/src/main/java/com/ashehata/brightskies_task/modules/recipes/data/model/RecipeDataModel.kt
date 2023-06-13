package com.ashehata.brightskies_task.modules.recipes.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Keep
@Entity(tableName = "favourite_recipes")
data class RecipeDataModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: String,
    @Json(name = "calories")
    val calories: String? = null,
    @Json(name = "carbos")
    val carbos: String? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "deliverable_ingredients")
    val deliverableIngredients: List<String?>? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "difficulty")
    val difficulty: Int? = null,
    @Json(name = "fats")
    val fats: String? = null,
    @Json(name = "favorites")
    val favorites: Int? = null,
    @Json(name = "fibers")
    val fibers: String? = null,
    @Json(name = "headline")
    val headline: String? = null,
    @Json(name = "highlighted")
    val highlighted: Boolean? = null,
    @Json(name = "image")
    val image: String? = null,
    @Json(name = "incompatibilities")
    val incompatibilities: String? = null,
    @Json(name = "ingredients")
    val ingredients: List<String?>? = null,
    @Json(name = "keywords")
    val keywords: List<String?>? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "products")
    val products: List<String?>? = null,
    @Json(name = "proteins")
    val proteins: String? = null,
    @Json(name = "rating")
    val rating: Double? = null,
    @Json(name = "ratings")
    val ratings: Double? = null,
    @Json(name = "time")
    val time: String? = null,
    @Json(name = "undeliverable_ingredients")
    val undeliverableIngredients: List<String?>? = null,
    @Json(name = "weeks")
    val weeks: List<String?>? = null
)