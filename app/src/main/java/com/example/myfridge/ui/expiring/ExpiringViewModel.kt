package com.example.myfridge.ui.expiring

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.fridge.FridgeContent
import com.example.myfridge.data.fridge.FridgeItemInfo
import kotlinx.coroutines.launch

class ExpiringViewModel : ViewModel() {

    private val _expiringContent = MutableLiveData<FridgeContent?>(null)
    val expiringContent: LiveData<FridgeContent?> = _expiringContent

    fun loadExpiringContent(){

        val tempContent: FridgeContent
        val tempList: MutableList<FridgeItemInfo> = mutableListOf()
        tempList.add(FridgeItemInfo("Image 1", "Expiring Soon Item 1", "Expiration 1"))
        tempList.add(FridgeItemInfo("Image 2", "Expiring Soon Item 2", "Expiration 2"))
        tempList.add(FridgeItemInfo("Image 3", "Expiring Soon Item 3", "Expiration 3"))
        tempList.add(FridgeItemInfo("Image 4", "Expiring Soon Item 4", "Expiration 4"))
        tempList.add(FridgeItemInfo("Image 5", "Expiring Soon Item 5", "Expiration 5"))
        tempList.add(FridgeItemInfo("Image 6", "Expiring Soon Item 6", "Expiration 6"))
        tempList.add(FridgeItemInfo("Image 7", "Expiring Soon Item 7", "Expiration 7"))
        tempList.add(FridgeItemInfo("Image 8", "Expiring Soon Item 8", "Expiration 8"))
        tempList.add(FridgeItemInfo("Image 9", "Expiring Soon Item 9", "Expiration 9"))
        tempList.add(FridgeItemInfo("Image 10", "Expiring Soon Item 10", "Expiration 10"))
        tempList.add(FridgeItemInfo("Image 11", "Expiring Soon Item 11", "Expiration 11"))
        tempList.add(FridgeItemInfo("Image 12", "Expiring Soon Item 12", "Expiration 12"))
        tempList.add(FridgeItemInfo("Image 13", "Expiring Soon Item 13", "Expiration 13"))
        tempList.add(FridgeItemInfo("Image 14", "Expiring Soon Item 14", "Expiration 14"))
        tempList.add(FridgeItemInfo("Image 15", "Expiring Soon Item 15", "Expiration 15"))
        tempList.add(FridgeItemInfo("Image 16", "Expiring Soon Item 16", "Expiration 16"))

        tempContent = FridgeContent(tempList)
        viewModelScope.launch {
            _expiringContent.value = tempContent
        }
    }
}