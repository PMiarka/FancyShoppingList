package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.room.ListItemLocalDto

internal interface UpdateListItemActions {
    suspend fun updateItem(listItem: ListItemLocalDto)
    suspend fun setItemFinished(listItem: ListItemLocalDto)
}
