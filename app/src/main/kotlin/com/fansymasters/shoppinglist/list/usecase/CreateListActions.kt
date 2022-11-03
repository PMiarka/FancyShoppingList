package com.fansymasters.shoppinglist.list.usecase

internal interface CreateListActions {
    suspend fun createList(name: String, description: String)
}
