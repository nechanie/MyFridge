package com.example.myfridge.data.recipes

import android.util.Log
import com.example.myfridge.api.RecipesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeSearch (
    private val service: RecipesService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadRecipeSearch(appid: String, ingredients:String): Result<List<RecipeItem>?> =
        withContext(dispatcher) {
            try {
                val response = service.getRecipesData(appid, ingredients)
                if (response.isSuccessful) {
                    Log.d("SEARCH", "${response.body()}")
                    Result.success(response.body())
                } else {
                    Log.d("SEARCH", "${response.errorBody()?.string()}")
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Log.d("FAIL", "FAIL")
                Result.failure(e)
            }
        }
}