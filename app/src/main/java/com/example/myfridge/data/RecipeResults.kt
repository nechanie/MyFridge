package com.example.myfridge.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResults (
    val list: List<RecipeItems>
)