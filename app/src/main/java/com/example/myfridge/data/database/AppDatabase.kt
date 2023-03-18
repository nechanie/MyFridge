package com.example.myfridge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[APICallInfo::class, FridgeItemInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun apiCallInfoDao(): DatabaseDAO.APICallInfoDAO

    abstract fun fridgeItemInfoDao(): DatabaseDAO.FridgeItemInfoDAO

    companion object{
        @Volatile private var instance: AppDatabase? = null

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "MyFridge.db").build()

        fun getInstance(context: Context): AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also{
                    instance = it
                }
            }
        }
    }
}