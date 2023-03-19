package com.example.myfridge.fridgewidget

import android.widget.CalendarView
import android.widget.DatePicker

data class FoodItem(val id: Long, val name:String, val expiration: DatePicker)