package com.example.myfridge.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.shopping.ShoppingItem
import com.example.myfridge.data.shopping.ShoppingList

class ShoppingAdapter: RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {
    var shoppingList = listOf<ShoppingItem>()

    override fun getItemCount() = shoppingList.size

    fun updateShoppingList(recipes: ShoppingList?) {
        shoppingList = recipes?.list ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.shoppingList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var shoppingName: TextView = view.findViewById(R.id.shopping_item_name)
        private var currentShoppingItem: ShoppingItem? = null


        fun bind(shoppingItem: ShoppingItem) {
            currentShoppingItem = shoppingItem

            shoppingName.text = shoppingItem.name
        }
    }
}