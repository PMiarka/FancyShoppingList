package com.fansymasters.shoppinglist.list.createitem.usecase

internal interface CreateItemActions {
    suspend fun createItem(name: String, listId: Int)
}
