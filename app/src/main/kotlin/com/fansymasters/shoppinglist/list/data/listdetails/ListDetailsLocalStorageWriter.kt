package com.fansymasters.shoppinglist.list.data.listdetails

import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto

internal interface ListDetailsLocalStorageWriter {
    suspend fun updateList(list: ListDetailsLocalDto)
    suspend fun addOrUpdateItem(item: ListItemLocalDto)
}