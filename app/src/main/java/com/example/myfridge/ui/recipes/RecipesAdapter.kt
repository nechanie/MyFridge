package com.example.myfridge.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.RecipeItems

class RecipesAdapter: RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {
    var recipesList = listOf<RecipeItems>()

    override fun getItemCount() = recipesList.size

    fun updateRecipesList(newRecipesList: List<RecipeItems>?) {
        recipesList = newRecipesList ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.recipesList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var currentRecipesItem: RecipeItems? = null

        fun bind(recipesItems: RecipeItems) {
            currentRecipesItem = recipesItems
        }
    }
}