package com.fansymasters.shoppinglist.list.domain.listdetails

import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import kotlinx.coroutines.flow.SharedFlow

internal interface ListDetailsLocalStorageReader {
    val localState: SharedFlow<ListDetailsLocalDto>
    suspend fun getItemById(itemId: Int): ListItemLocalDto?
}