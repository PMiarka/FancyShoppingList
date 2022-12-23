package com.fansymasters.shoppinglist.list.data.lists

import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto

internal interface ListsLocalStorageWriter {
    fun updateLists(lists: List<ListLocalDto>)
}