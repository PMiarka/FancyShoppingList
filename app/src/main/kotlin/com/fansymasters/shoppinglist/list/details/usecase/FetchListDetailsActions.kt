package com.fansymasters.shoppinglist.list.details.usecase

internal interface FetchListDetailsActions {
    suspend fun fetchListDetails(listId: Int)
}
