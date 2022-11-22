package com.fansymasters.shoppinglist.list.domain

import com.fansymasters.shoppinglist.data.ApiResult
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import kotlinx.coroutines.flow.Flow

internal interface ListDetailsRepository {
    val localState: Flow<List<ListItemLocalDto>>
    suspend fun fetchListItems(listId: Int): ApiResult<Unit>
    suspend fun deleteItem(itemId: Int): ApiResult<Unit>
}