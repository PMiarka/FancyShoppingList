package com.fansymasters.shoppinglist.list.data.lists

import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto
import com.fansymasters.shoppinglist.list.domain.lists.ListsLocalStorageReader
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListsLocalStorageImpl @Inject constructor() :
    ListsLocalStorageWriter,
    ListsLocalStorageReader {
    override val state = MutableSharedFlow<List<ListLocalDto>>(extraBufferCapacity = 1)

    override fun updateLists(lists: List<ListLocalDto>) {
        state.tryEmit(lists)
    }
}