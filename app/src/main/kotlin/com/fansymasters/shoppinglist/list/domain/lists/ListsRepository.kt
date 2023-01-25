package com.fansymasters.shoppinglist.list.domain.lists

internal interface ListsRepository {
    suspend fun fetchLists()
    suspend fun createList(name: String, description: String)
}
