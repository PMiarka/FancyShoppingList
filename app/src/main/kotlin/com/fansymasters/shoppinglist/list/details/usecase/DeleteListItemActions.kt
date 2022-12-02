package com.fansymasters.shoppinglist.list.details.usecase

internal interface DeleteListItemActions {
    suspend fun deleteItem(itemId: Int)
}
