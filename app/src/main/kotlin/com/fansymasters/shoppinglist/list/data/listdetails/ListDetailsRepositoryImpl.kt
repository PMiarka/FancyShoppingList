package com.fansymasters.shoppinglist.list.data.listdetails

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.common.noMapper
import com.fansymasters.shoppinglist.common.unitMapper
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListDetailsRepositoryImpl @Inject constructor(
    private val api: ListsApi,
    private val localStorageWriter: ListDetailsLocalStorageWriter,
    private val listItemMapper: Mapper<ListItemDto, ListItemLocalDto>,
    private val detailsMapper: Mapper<ListDetailsDto, ListDetailsLocalDto>,
) : ListDetailsRepository {

    override suspend fun fetchListItems(listId: Int) {
        apiCall(noMapper()) { api.fetchListDetails(listId) }
            .also { details ->
                localStorageWriter.updateList(detailsMapper.map(details))
            }
    }

    override suspend fun deleteItem(itemId: Int) {
        apiCall(unitMapper()) { api.deleteItem(itemId) }
    }

    override suspend fun setItemFinished(itemId: Int, isFinished: Boolean) {
        apiCall(noMapper()) { api.setItemFinished(itemId, isFinished) }
    }

    override suspend fun updateItem(item: ListItemLocalDto) {
        apiCall(noMapper()) { api.updateItem(itemId = item.id, item.toCreateListItem()) }
    }

    private fun ListItemLocalDto.toCreateListItem(): CreateListItemDto = with(this) {
        CreateListItemDto(name, qty, sortNo, unit, category, finished)
    }
}