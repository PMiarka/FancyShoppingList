package com.fansymasters.shoppinglist.list.createitem.domain

import com.fansymasters.shoppinglist.data.ApiResult
import com.fansymasters.shoppinglist.data.lists.di.Category

internal interface CreateListItemRepository {
    suspend fun createListItem(
        listId: Int,
        name: String,
        unit: String,
        quantity: Int,
        category: Category,
    ): ApiResult<Unit>
}