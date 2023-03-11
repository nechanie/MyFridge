package com.example.myfridge.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.api.RecipesService
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.data.recipes.RecipeSearch
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {

    private val recipe = RecipeSearch(RecipesService.create())

    private val _recipes = MutableLiveData<List<RecipeItem>?>()
    val recipes: LiveData<List<RecipeItem>?> = _recipes

    fun loadRecipeResults(appid: String) {
        viewModelScope.launch {
            val result = recipe.loadRecipeSearch(appid)
            _recipes.value = result.getOrNull()
            Log.d("RecipesViewModel", "${_recipes.value}")
        }
    }
}