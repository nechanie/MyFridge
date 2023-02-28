package com.example.myfridge.data.recipes

import com.example.myfridge.data.recipes.RecipeItems
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResults (
    val list: List<RecipeItems>
)