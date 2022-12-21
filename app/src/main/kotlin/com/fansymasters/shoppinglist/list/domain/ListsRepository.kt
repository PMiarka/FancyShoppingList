package com.fansymasters.shoppinglist.list.domain

internal interface ListsRepository {
    suspend fun fetchLists()
}
