package com.fansymasters.shoppinglist.list.createitem.usecase

import com.fansymasters.shoppinglist.data.lists.di.Category

internal interface CreateItemActions {
    suspend fun createItem(
        name: String,
        unit: String,
        quantity: Int,
        category: Category,
        listId: Int
    )
}
