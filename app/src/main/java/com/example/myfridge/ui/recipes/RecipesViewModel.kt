package com.example.myfridge.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.data.recipes.RecipeResults
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {

    private val _recipes = MutableLiveData<RecipeResults?>()
    val recipes: LiveData<RecipeResults?> = _recipes

    fun loadRecipeResults(){
        val tempResults: RecipeResults
        val tempList: MutableList<RecipeItem> = mutableListOf()
        tempList.add(RecipeItem(1, "Recipe Item 1 name"))
        tempList.add(RecipeItem(2, "Recipe Item 2 name"))
        tempList.add(RecipeItem(3, "Recipe Item 3 name"))
        tempList.add(RecipeItem(4, "Recipe Item 4 name"))
        tempList.add(RecipeItem(5, "Recipe Item 5 name"))
        tempList.add(RecipeItem(6, "Recipe Item 6 name"))
        tempList.add(RecipeItem(7, "Recipe Item 7 name"))
        tempList.add(RecipeItem(8, "Recipe Item 8 name"))
        tempList.add(RecipeItem(9, "Recipe Item 9 name"))
        tempList.add(RecipeItem(10, "Recipe Item 10 name"))
        tempList.add(RecipeItem(11, "Recipe Item 11 name"))
        tempList.add(RecipeItem(12, "Recipe Item 12 name"))
        tempList.add(RecipeItem(13, "Recipe Item 13 name"))
        tempList.add(RecipeItem(14, "Recipe Item 14 name"))
        tempList.add(RecipeItem(15, "Recipe Item 15 name"))

        tempResults = RecipeResults(tempList)
        viewModelScope.launch {
            _recipes.value = tempResults
        }
    }
}