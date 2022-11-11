package com.fansymasters.shoppinglist.list.createlist.usecase

internal interface CreateListActions {
    suspend fun createList(name: String, description: String)
}
