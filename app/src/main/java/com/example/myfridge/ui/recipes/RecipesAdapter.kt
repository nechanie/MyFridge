package com.example.myfridge.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.data.recipes.RecipeResults

class RecipesAdapter(private val onClickFunc: (RecipeItem) -> Unit): RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {
    var recipesList = listOf<RecipeItem>()

    override fun getItemCount() = recipesList.size

    fun updateRecipesList(recipes: RecipeResults?) {
        recipesList = recipes?.list ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return ViewHolder(view, onClickFunc)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.recipesList[position])
    }

    class ViewHolder(view: View, private val onClickFunc: (RecipeItem) -> Unit) : RecyclerView.ViewHolder(view) {
        private var recipeName: TextView = view.findViewById(R.id.recipe_item_name)
        private var currentRecipeItem: RecipeItem? = null

        init {
            view.setOnClickListener { currentRecipeItem?.let(onClickFunc) }
        }
        fun bind(recipe: RecipeItem) {
            currentRecipeItem = recipe

            recipeName.text = recipe.title
        }
    }
}