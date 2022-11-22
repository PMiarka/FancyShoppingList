package com.fansymasters.shoppinglist.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListItemsDeletedDao {
    @Query("SELECT * FROM listItemDeletedDto")
    suspend fun getAll(): List<ListItemDeletedDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDeletedItem(item: ListItemDeletedDto)
}