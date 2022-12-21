package com.fansymasters.shoppinglist.list.domain

import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import kotlinx.coroutines.flow.Flow

internal interface ListDetailsRepository {
    val localState: Flow<ListDetailsLocalDto>
    suspend fun fetchListItems(listId: Int)
    suspend fun updateItem(item: ListItemLocalDto)
    suspend fun setItemFinished(itemId: Int, isFinished: Boolean)
    suspend fun deleteItem(itemId: Int)
}