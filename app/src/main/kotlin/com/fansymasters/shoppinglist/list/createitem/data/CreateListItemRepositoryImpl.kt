package com.fansymasters.shoppinglist.list.createitem.data

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.room.CreateListItemLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import com.fansymasters.shoppinglist.list.data.listdetails.ListDetailsLocalStorageWriter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CreateListItemRepositoryImpl @Inject constructor(
    private val api: ListsApi,
    private val localStorageWriter: ListDetailsLocalStorageWriter,
    private val mapper: Mapper<ListItemDto, ListItemLocalDto>,
    private val mapperCreateListItem: Mapper<CreateListItemLocalDto, CreateListItemDto>,
) : CreateListItemRepository {
    override suspend fun createListItem(
        item: CreateListItemLocalDto,
        listId: Int,
    ) {
        val responseItem: ListItemLocalDto = apiCall(mapper::map) {
            if (item.id != -1) {
                api.updateItem(item.id, mapperCreateListItem.map(item))
                mapCreateItemToItem(item)
            } else {
                api.createItem(listId, mapperCreateListItem.map(item))
            }
        }
        localStorageWriter.addOrUpdateItem(responseItem)
    }

    private fun mapCreateItemToItem(item: CreateListItemLocalDto): ListItemDto = with(item) {
        ListItemDto(
            id = id,
            name = name,
            qty = qty,
            sortNo = 200,
            unit = unit,
            category = category.apiKey,
            finished = finished
        )
    }
}