package com.fansymasters.shoppinglist.list.data

import android.util.Log
import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.ApiResult
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.onSuccessSuspend
import com.fansymasters.shoppinglist.data.room.ListItemDeletedDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemsDeletedDao
import com.fansymasters.shoppinglist.data.room.ListItemsLocalDao
import com.fansymasters.shoppinglist.data.toUnit
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListDetailsRepositoryImpl @Inject constructor(
    private val api: ListsApi,
    private val listItemsLocalDao: ListItemsLocalDao,
    private val listItemsDeletedDao: ListItemsDeletedDao,
    private val mapper: Mapper<ListItemDto, ListItemLocalDto>
) : ListDetailsRepository {
    override val localState = listItemsLocalDao.getAllFlow()

    override suspend fun fetchListItems(listId: Int): ApiResult<Unit> =
        apiCall { api.fetchListDetails(listId) }
            .onSuccessSuspend {
                val mappedValues: List<ListItemLocalDto> =
                    it.shopListItems.map { mapper.map(it) }
                try {
                    synchronize(mappedValues)
                } catch (e: Throwable) {
                    println("$e")
                }
            }
            .toUnit()

    override suspend fun deleteItem(itemId: Int): ApiResult<Unit> {
//        apiCall { api.deleteItem(itemId) }
        try {
            listItemsDeletedDao.addDeletedItem(ListItemDeletedDto(itemId))
            listItemsLocalDao.delete(itemId)
        } catch (e: Throwable) {
            println("$e")
        }
        return ApiResult.Success(Unit)
    }

    private suspend fun synchronize(remoteItems: List<ListItemLocalDto>) {
        val localItems = listItemsLocalDao.getAll()
        val locallyDeletedItemsIds = listItemsDeletedDao.getAll().map { it.id }

        remoteItems
            .forEach { remoteItem ->
                val localItem = localItems.firstOrNull { it.id == remoteItem.id }
                when {
                    localItem == null && !locallyDeletedItemsIds.contains(remoteItem.id) -> {
                        Log.e("Piotrek", "localItem not found, not deleted locally: $remoteItem")
                        listItemsLocalDao.insertAll(remoteItem)
                    }
                    localItem == null && locallyDeletedItemsIds.contains(remoteItem.id) -> {
                        Log.e("Piotrek", "localItem not found, deleted locally: $remoteItem")
                        coroutineScope { launch { apiCall { api.deleteItem(remoteItem.id) } } }
                    }
                    localItem != null && localItem.equals(remoteItem) ->
                        Log.e("Piotrek", "localItem found everything iss the same")

                    localItem != null && !localItem.equals(remoteItem) -> {
                        Log.e("Piotrek", "localItem found, has to be updated: $remoteItem")
                        listItemsLocalDao.updateItem(remoteItem)
                    }
                }
            }
    }
}