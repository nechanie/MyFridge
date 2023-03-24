package com.example.myfridge.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.database.ShoppingListItemInfo
import com.example.myfridge.data.shopping.ShoppingItem
import com.example.myfridge.data.shopping.ShoppingList

class ShoppingAdapter(private val onDeleteButtonClick: (ShoppingListItemInfo) -> Unit): RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {
    var shoppingList = listOf<ShoppingListItemInfo>()

    override fun getItemCount() = shoppingList.size

    fun updateShoppingList(newList: List<ShoppingListItemInfo>?) {
        shoppingList = newList ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_list_item, parent, false)
        return ViewHolder(view, onDeleteButtonClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.shoppingList[position])
    }

    class ViewHolder(view: View, private val onDeleteButtonClick: (ShoppingListItemInfo) -> Unit) : RecyclerView.ViewHolder(view) {
        private var shoppingName: TextView = view.findViewById(R.id.shopping_item_name)
        private var delButton: Button = view.findViewById(R.id.delete_shopping_item_button)
        private var currentShoppingItem: ShoppingListItemInfo? = null

        init{
            delButton.setOnClickListener(){
                onDeleteButtonClick(currentShoppingItem!!)
            }
        }

        fun bind(shoppingItem: ShoppingListItemInfo) {
            currentShoppingItem = shoppingItem
            shoppingName.text = shoppingItem.name
        }
    }
}