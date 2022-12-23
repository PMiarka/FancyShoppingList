package com.fansymasters.shoppinglist.list.data.listdetails

import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto

internal interface ListDetailsLocalStorageWriter {
    suspend fun updateList(list: ListDetailsLocalDto)
}