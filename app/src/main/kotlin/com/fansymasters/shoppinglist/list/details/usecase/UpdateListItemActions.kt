package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.lists.ListItemDto

internal interface UpdateListItemActions {
    suspend fun updateItem(listItem: ListItemDto, finished: Boolean)
}
