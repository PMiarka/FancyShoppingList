package com.fansymasters.shoppinglist.list.data.lists

import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto

internal interface ListsLocalStorageWriter {
    suspend fun updateLists(lists: List<ListLocalDto>)
    suspend fun addNewlyCreateList(list: ListLocalDto)
}