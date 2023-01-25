package com.fansymasters.shoppinglist.list.data.listdetails

import android.util.Log
import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsLocalStorageReader
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListDetailsLocalStorageImpl @Inject constructor() :
    ListDetailsLocalStorageWriter,
    ListDetailsLocalStorageReader {
    override val localState =
        MutableSharedFlow<ListDetailsLocalDto>(extraBufferCapacity = 1, replay = 1)

    private fun ListDetailsLocalDto.sortListItems() =
        copy(shopListItems = this.shopListItems.sortedBy { it.finished })

    override suspend fun updateList(list: ListDetailsLocalDto) {
        localState.emit(list.sortListItems())
    }

    override suspend fun addCreatedItem(item: ListItemLocalDto) {
        localState.firstOrNull()?.let { details ->
            val updatedItems = details.shopListItems.toMutableList().also { it.add(item) }
            localState.emit(details.copy(shopListItems = updatedItems).sortListItems())
        }
    }
}