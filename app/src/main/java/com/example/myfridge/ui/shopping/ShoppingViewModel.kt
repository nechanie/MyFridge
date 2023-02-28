package com.example.myfridge.ui.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.shopping.ShoppingItem
import com.example.myfridge.data.shopping.ShoppingList
import kotlinx.coroutines.launch

class ShoppingViewModel : ViewModel() {

    private val _shoppingList = MutableLiveData<ShoppingList?>()
    val shoppingList: LiveData<ShoppingList?> = _shoppingList

    fun loadShoppingList(){
        val tempResults: ShoppingList
        val tempList: MutableList<ShoppingItem> = mutableListOf()
        tempList.add(ShoppingItem("Shopping Item 1 name"))
        tempList.add(ShoppingItem("Shopping Item 2 name"))
        tempList.add(ShoppingItem("Shopping Item 3 name"))
        tempList.add(ShoppingItem("Shopping Item 4 name"))
        tempList.add(ShoppingItem("Shopping Item 5 name"))
        tempList.add(ShoppingItem("Shopping Item 6 name"))
        tempList.add(ShoppingItem("Shopping Item 7 name"))
        tempList.add(ShoppingItem("Shopping Item 8 name"))
        tempList.add(ShoppingItem("Shopping Item 9 name"))
        tempList.add(ShoppingItem("Shopping Item 10 name"))
        tempList.add(ShoppingItem("Shopping Item 11 name"))
        tempList.add(ShoppingItem("Shopping Item 12 name"))
        tempList.add(ShoppingItem("Shopping Item 13 name"))
        tempList.add(ShoppingItem("Shopping Item 14 name"))
        tempList.add(ShoppingItem("Shopping Item 15 name"))

        tempResults = ShoppingList(tempList)
        viewModelScope.launch {
            _shoppingList.value = tempResults
        }
    }
}