package com.example.myfridge.api

import com.example.myfridge.data.RecipeResults
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {

    @GET("findByIngredients")
    fun getRecipesData (
        @Query("ingredients") ingredients : String = "apples"
    ) : Call<RecipeResults>

    companion object {
        private const val BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes"

        fun create(): RecipesService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RecipesService::class.java)
        }
    }
}