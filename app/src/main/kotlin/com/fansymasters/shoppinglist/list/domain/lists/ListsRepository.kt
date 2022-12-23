package com.fansymasters.shoppinglist.list.domain.lists

internal interface ListsRepository {
    suspend fun fetchLists()
}
