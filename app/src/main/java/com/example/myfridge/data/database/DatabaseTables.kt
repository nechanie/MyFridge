package com.example.myfridge.data.database

import android.widget.CalendarView
import android.widget.DatePicker
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
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
    val img: ByteArray,
    @PrimaryKey
    val name: String,
    val exp: Long,

    ) : java.io.Serializable {
    @Ignore
    var showingMenu: Boolean = false
    }