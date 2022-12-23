package com.fansymasters.shoppinglist.list.data.listdetails

import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsLocalStorageReader
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListDetailsLocalStorageImpl @Inject constructor() :
    ListDetailsLocalStorageWriter,
    ListDetailsLocalStorageReader {
    override val localState = MutableSharedFlow<ListDetailsLocalDto>(extraBufferCapacity = 1)

    override suspend fun updateList(list: ListDetailsLocalDto) {
        localState.emit(list)
    }
}