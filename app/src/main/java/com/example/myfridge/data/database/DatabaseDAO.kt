package com.example.myfridge.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


class DatabaseDAO{
    @Dao
    interface APICallInfoDAO{
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(callInfo: APICallInfo)

        @Delete
        suspend fun delete(city: APICallInfo)

        @Update
        suspend fun update(city: APICallInfo)

        @Query("SELECT * FROM APICallInfo WHERE ingredients = :ingredients LIMIT 1")
        fun query(ingredients:String): Flow<APICallInfo?>

        @Query("SELECT * FROM APICallInfo ORDER BY timestamp DESC")
        fun queryAll(): Flow<List<APICallInfo>?>
    }
}