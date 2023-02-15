package com.fansymasters.shoppinglist.list.createitem.usecase

import com.fansymasters.shoppinglist.data.room.CreateListItemLocalDto


internal interface CreateItemActions {
    suspend fun createItem(item: CreateListItemLocalDto, listId: Int)
    suspend fun getInitialItem(itemId: Int) : CreateListItemLocalDto
}
