package com.example.myfridge.ui.database

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.database.*
import kotlinx.coroutines.launch



class DatabaseViewModel {

    class APICallInfoViewModel(application: Application): AndroidViewModel(application){
        private val repository = DatabaseRepository.APICallInfoRepository(
            AppDatabase.getInstance(application).apiCallInfoDao()
        )
        val apiCallInfoHistory: LiveData<List<APICallInfo>?> = repository.getAPICallHistory.asLiveData()

        fun addAPICallInfo(city: APICallInfo){
            viewModelScope.launch{
                repository.insertAPICallInfo(city)
            }
        }

        fun deleteAPICallInfo(city: APICallInfo){
            viewModelScope.launch{
                repository.deleteAPICallInfo(city)
            }
        }

        fun updateAPICallInfo(city: APICallInfo){
            viewModelScope.launch{
                repository.updateAPICallInfo(city)
            }
        }

        fun getAPICallInfo(callInfo: String) = repository.getAPICallInfo(callInfo).asLiveData()
    }

    class FridgeItemInfoViewModel(application: Application): AndroidViewModel(application){
        private val repository = DatabaseRepository.FridgeItemInfoRepository(
            AppDatabase.getInstance(application).fridgeItemInfoDao()
        )
        val fridgeItemInfoAll: LiveData<List<FridgeItemInfo>?> = repository.getAllFridgeItems.asLiveData()

        fun addFridgeItemInfo(fridgeItem: FridgeItemInfo){
            viewModelScope.launch{
                repository.insertFridgeItemInfo(fridgeItem)
            }
        }

        fun deleteFridgeItemInfo(fridgeItem: FridgeItemInfo){
            viewModelScope.launch{
                repository.deleteFridgeItemInfo(fridgeItem)
            }
        }

        fun updateFridgeItemInfo(fridgeItem: FridgeItemInfo){
            viewModelScope.launch{
                repository.updateFridgeItemInfo(fridgeItem)
            }
        }

        fun getExpiringSoon(soonDate:Long) = repository.getExpiringSoon(soonDate).asLiveData()
        fun getFridgeItemInfo(fridgeInfo: String) = repository.getFridgeItemInfo(fridgeInfo).asLiveData()
    }

//    class ShoppingListViewModel(application: Application): AndroidViewModel(application){
//        private val repository = DatabaseRepository.ShoppingListRepository(
//            AppDatabase.getInstance(application).shoppingListDao()
//        )
//        val shoppingLists: LiveData<List<ShoppingList>?> = repository.getAllShoppingLists.asLiveData()
//
//        fun addShoppingList(shoppingList: ShoppingList){
//            viewModelScope.launch{
//                repository.insertShoppingList(shoppingList)
//            }
//        }
//
//        fun deleteShoppingList(shoppingList: ShoppingList){
//            viewModelScope.launch{
//                repository.deleteShoppingList(shoppingList)
//            }
//        }
//
//        fun updateShoppingListo(shoppingList: ShoppingList){
//            viewModelScope.launch{
//                repository.updateShoppingList(shoppingList)
//            }
//        }
//        fun getShoppingList(name: String) = repository.getShoppingList(name).asLiveData()
//    }

}