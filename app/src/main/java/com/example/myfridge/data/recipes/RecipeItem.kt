package com.example.myfridge.data.recipes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeItem (
    @Json(name="id") val id: Int,
    @Json(name="title") val title: String
) : java.io.Serializable