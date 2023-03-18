package com.example.myfridge.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class APICallInfo(
    @PrimaryKey
    val ingredients: String,
    val timestamp: Long
) : java.io.Serializable

@Entity
data class FridgeItemInfo(
    val img: String,
    @PrimaryKey
    val name: String,
    val exp: String
    ) : java.io.Serializable