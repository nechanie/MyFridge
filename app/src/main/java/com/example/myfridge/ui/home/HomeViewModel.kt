package com.example.myfridge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.home.FridgeContent
import com.example.myfridge.data.home.FridgeItemInfo
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _fridgeContent = MutableLiveData<FridgeContent?>(null)
    val fridgeContent: LiveData<FridgeContent?> = _fridgeContent

    fun loadFridgeContent(){

        val tempContent: FridgeContent
        val tempList: MutableList<FridgeItemInfo> = mutableListOf<FridgeItemInfo>()
        tempList.add(FridgeItemInfo("Image 1", "Name/Description 1", "Expiration 1"))
        tempList.add(FridgeItemInfo("Image 2", "Name/Description 2", "Expiration 2"))
        tempList.add(FridgeItemInfo("Image 3", "Name/Description 3", "Expiration 3"))
        tempList.add(FridgeItemInfo("Image 4", "Name/Description 4", "Expiration 4"))
        tempList.add(FridgeItemInfo("Image 5", "Name/Description 5", "Expiration 5"))
        tempList.add(FridgeItemInfo("Image 6", "Name/Description 6", "Expiration 6"))
        tempList.add(FridgeItemInfo("Image 7", "Name/Description 7", "Expiration 7"))
        tempList.add(FridgeItemInfo("Image 8", "Name/Description 8", "Expiration 8"))
        tempList.add(FridgeItemInfo("Image 9", "Name/Description 9", "Expiration 9"))
        tempList.add(FridgeItemInfo("Image 10", "Name/Description 10", "Expiration 10"))
        tempList.add(FridgeItemInfo("Image 11", "Name/Description 11", "Expiration 11"))
        tempList.add(FridgeItemInfo("Image 12", "Name/Description 12", "Expiration 12"))
        tempList.add(FridgeItemInfo("Image 13", "Name/Description 13", "Expiration 13"))
        tempList.add(FridgeItemInfo("Image 14", "Name/Description 14", "Expiration 14"))
        tempList.add(FridgeItemInfo("Image 15", "Name/Description 15", "Expiration 15"))
        tempList.add(FridgeItemInfo("Image 16", "Name/Description 16", "Expiration 16"))

        tempContent = FridgeContent(tempList)
        viewModelScope.launch {
            _fridgeContent.value = tempContent
        }
    }
}