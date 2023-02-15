package com.fansymasters.shoppinglist.list.data.listdetails

import android.util.Log
import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsLocalStorageReader
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListDetailsLocalStorageImpl @Inject constructor() :
    ListDetailsLocalStorageWriter,
    ListDetailsLocalStorageReader {
    override val localState =
        MutableSharedFlow<ListDetailsLocalDto>(extraBufferCapacity = 0, replay = 1)

    private fun ListDetailsLocalDto.sortListItems() =
        copy(shopListItems = this.shopListItems.sortedBy { it.finished })

    override suspend fun getItemById(itemId: Int): ListItemLocalDto? =
        localState.firstOrNull()?.shopListItems?.firstOrNull { it.id == itemId }

    override suspend fun updateList(list: ListDetailsLocalDto) {
        localState.emit(list.sortListItems())
    }

    override suspend fun addOrUpdateItem(item: ListItemLocalDto) {
        localState.firstOrNull()?.let { details ->
            val updatedItems = details.shopListItems.toMutableList().also { list ->
                if (list.any { it.id == item.id }) {
                    val index = list.indexOfFirst { it.id == item.id }
                    list[index] = item
                } else {
                    list.add(item)
                }
            }
            localState.emit(details.copy(shopListItems = updatedItems).sortListItems())
        }
    }
}