package com.example.myfridge.ui.database

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.database.APICallInfo
import com.example.myfridge.data.database.AppDatabase
import com.example.myfridge.data.database.DatabaseRepository
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
}