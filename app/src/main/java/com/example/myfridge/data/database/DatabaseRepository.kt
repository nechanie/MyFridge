package com.example.myfridge.data.database

import com.example.myfridge.data.recipes.RecipeDetailedItem

class DatabaseRepository {

    class APICallInfoRepository(
        private val dao: DatabaseDAO.APICallInfoDAO
    ) {
        suspend fun insertAPICallInfo(callInfo:APICallInfo) = dao.insert(callInfo)

        suspend fun deleteAPICallInfo(callInfo:APICallInfo) = dao.delete(callInfo)

        suspend fun updateAPICallInfo(callInfo:APICallInfo) = dao.update(callInfo)

        fun getAPICallInfo(callInfo: String) = dao.query(callInfo)

        val getAPICallHistory = dao.queryAll()
    }

    class FridgeItemInfoRepository(
        private val dao: DatabaseDAO.FridgeItemInfoDAO
    ){
        suspend fun insertFridgeItemInfo(fridgeInfo:FridgeItemInfo) = dao.insert(fridgeInfo)

        suspend fun deleteFridgeItemInfo(fridgeInfo:FridgeItemInfo) = dao.delete(fridgeInfo)

        suspend fun updateFridgeItemInfo(fridgeInfo:FridgeItemInfo) = dao.update(fridgeInfo)

        fun getFridgeItemInfo(fridgeItemName: String) = dao.query(fridgeItemName)

        fun getExpiringSoon(soonDate:Long) = dao.expiringSoon(soonDate)

        val getAllFridgeItems = dao.queryAll()
        val getAllFridgeItemNames = dao.queryAllNames()
    }

//    class ShoppingListRepository(
//        private val dao: DatabaseDAO.ShoppingListDAO
//    ){
//        suspend fun insertShoppingList(shoppingList:ShoppingList) = dao.insert(shoppingList)
//
//        suspend fun deleteShoppingList(shoppingList:ShoppingList) = dao.delete(shoppingList)
//
//        suspend fun updateShoppingList(shoppingList:ShoppingList) = dao.update(shoppingList)
//
//        fun getShoppingList(name: String) = dao.query(name)
//
//        val getAllShoppingLists = dao.queryAll()
//    }
}