package com.example.myfridge.data.recipes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResults (
    val list: List<RecipeItem>
)