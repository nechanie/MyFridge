package com.example.myfridge.data.recipes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeItem (
    @Json(name="id") val id: Int,
    @Json(name="image") val image: String,
    @Json(name="imageType") val imageType: String,
    @Json(name="likes") val likes: Int,
    @Json(name="missedIngredientCount") val missedIngredientCount: Int,
    @Json(name="missedIngredients") val missedIngredients: List<MissedIngredients>,
    @Json(name="title") val title: String
) : java.io.Serializable

@JsonClass(generateAdapter = true)
data class MissedIngredients (
    @Json(name="aisle") val aisle: String,
    @Json(name="amount") val amount: Float,
    @Json(name="id") val id: Int,
    @Json(name="image") val image: String,
    @Json(name="meta") val meta: List<String>,
    @Json(name="name") val name: String,
    @Json(name="original") val original: String,
    @Json(name="originalName") val originalName: String,
    @Json(name="unit") val unit: String,
    @Json(name="unitLong") val unitLong: String,
    @Json(name="unitShort") val unitShort: String
) : java.io.Serializable