package com.fansymasters.shoppinglist.list.createitem.domain

import com.fansymasters.shoppinglist.data.lists.Category

internal interface CreateListItemRepository {
    suspend fun createListItem(
        listId: Int,
        name: String,
        unit: String,
        quantity: Int,
        category: Category,
    )
}