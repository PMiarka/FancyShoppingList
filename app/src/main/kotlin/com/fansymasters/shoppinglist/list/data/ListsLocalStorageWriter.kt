package com.fansymasters.shoppinglist.list.data

import com.fansymasters.shoppinglist.list.domain.ListLocalDto

internal interface ListsLocalStorageWriter {
    fun updateLists(lists: List<ListLocalDto>)
}