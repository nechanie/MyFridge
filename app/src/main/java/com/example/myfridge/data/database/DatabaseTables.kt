package com.example.myfridge.data.database

import android.widget.CalendarView
import android.widget.DatePicker
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.sql.Blob

@Entity
data class APICallInfo(
    @PrimaryKey
    val ingredients: String,
    val timestamp: Long
) : java.io.Serializable

@Entity
data class FridgeItemInfo(
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val img: ByteArray?,
    @PrimaryKey
    val name: String,
    val exp: Long?,
    ) : java.io.Serializable {
    @Ignore
    var showingMenu: Boolean = false
    }

@Entity
data class RecipeDetailedItemInfo(
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val sourceUrl: String = "",
    val aggregateLikes: Int,
    val summary: String = ""
    ) : java.io.Serializable