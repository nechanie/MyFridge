package com.example.myfridge.data.recipes

import android.util.Log
import com.example.myfridge.api.RecipesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeDetailedSearch (
    private val service: RecipesService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadDetailedRecipeSearch(id: Int, appid: String): Result<RecipeDetailedItem?> =
    withContext(dispatcher) {
        try {
            val response = service.getRecipesDetailed(id, appid)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}