package com.example.myfridge.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class APICallInfo(
    @PrimaryKey
    val ingredients: String,
    val timestamp: Long
) : java.io.Serializable