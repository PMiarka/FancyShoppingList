package com.fansymasters.shoppinglist.list.domain.listdetails

import com.fansymasters.shoppinglist.data.room.ListItemLocalDto

internal interface ListDetailsRepository {
    suspend fun fetchListItems(listId: Int)
    suspend fun updateItem(item: ListItemLocalDto)
    suspend fun setItemFinished(itemId: Int, isFinished: Boolean)
    suspend fun deleteItem(itemId: Int)
}