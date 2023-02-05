package com.fansymasters.shoppinglist.list.createitem.domain

import com.fansymasters.shoppinglist.data.room.CreateListItemLocalDto

internal interface CreateListItemRepository {
    suspend fun createListItem(
        item: CreateListItemLocalDto,
        listId: Int
    )
}
