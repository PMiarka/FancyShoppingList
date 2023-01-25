package com.fansymasters.shoppinglist.list.data.lists

import android.util.Log
import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto
import com.fansymasters.shoppinglist.list.domain.lists.ListsLocalStorageReader
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListsLocalStorageImpl @Inject constructor() :
    ListsLocalStorageWriter,
    ListsLocalStorageReader {
    override val state = MutableSharedFlow<List<ListLocalDto>>(extraBufferCapacity = 1, replay = 1)

    override suspend fun updateLists(lists: List<ListLocalDto>) {
        state.emit(lists)
    }

    override suspend fun addNewlyCreateList(newList: ListLocalDto) {
        state.firstOrNull()?.let { list ->
            val updatedList = list.toMutableList().also { it.add(newList) }
            state.emit(updatedList)
        }
    }
}