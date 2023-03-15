package com.example.myfridge.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.api.RecipesService
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.data.recipes.RecipeResults
import com.example.myfridge.data.recipes.RecipeSearch
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    private val recipe = RecipeSearch(RecipesService.create())
    private val _recipes = MutableLiveData<RecipeResults?>()
    val recipes: LiveData<RecipeResults?> = _recipes

    fun loadRecipeResults(appid: String){
        viewModelScope.launch {
            _recipes.value = RecipeResults(recipe.loadRecipeSearch(appid).getOrNull())
        }
    }
}