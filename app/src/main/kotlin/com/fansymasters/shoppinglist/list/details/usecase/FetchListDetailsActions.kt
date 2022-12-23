package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import kotlinx.coroutines.flow.SharedFlow

internal interface FetchListDetailsActions {
    val state: SharedFlow<ListDetailsLocalDto>
    suspend fun fetchListDetails(listId: Int)
}
