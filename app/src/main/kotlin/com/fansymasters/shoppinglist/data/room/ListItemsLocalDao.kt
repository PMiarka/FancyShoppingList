package com.fansymasters.shoppinglist.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemsLocalDao {
    @Query("SELECT * FROM listItemLocalDto")
    fun getAllFlow(): Flow<List<ListItemLocalDto>>

    @Query("SELECT * FROM listItemLocalDto")
    suspend fun getAll(): List<ListItemLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg listItemLocalDtos: ListItemLocalDto)

    @Update
    suspend fun updateItem(listItemLocalDto: ListItemLocalDto)

    @Query("DELETE FROM listItemLocalDto WHERE id = :itemId")
    suspend fun delete(itemId: Int)
}