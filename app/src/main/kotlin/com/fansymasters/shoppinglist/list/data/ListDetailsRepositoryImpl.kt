package com.fansymasters.shoppinglist.list.data

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.common.noMapper
import com.fansymasters.shoppinglist.common.unitMapper
import com.fansymasters.shoppinglist.data.ApiResult
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.onSuccessSuspend
import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.data.toUnit
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListDetailsRepositoryImpl @Inject constructor(
    private val api: ListsApi,
    private val listItemMapper: Mapper<ListItemDto, ListItemLocalDto>,
    private val detailsMapper: Mapper<ListDetailsDto, ListDetailsLocalDto>,
) : ListDetailsRepository {
    override val localState = MutableSharedFlow<ListDetailsLocalDto>()

    override suspend fun fetchListItems(listId: Int): ApiResult<Unit> =
        apiCall(noMapper()) { api.fetchListDetails(listId) }
            .onSuccessSuspend { details ->
                val sortedDetails =
                    details.copy(shopListItems = details.shopListItems.sortedBy { it.finished })
                localState.emit(detailsMapper.map(sortedDetails))
            }
            .toUnit()

    override suspend fun deleteItem(itemId: Int): ApiResult<Unit> =
        apiCall(unitMapper()) { api.deleteItem(itemId) }
            .toUnit()

    override suspend fun setItemFinished(itemId: Int, isFinished: Boolean): ApiResult<Unit> =
        apiCall(noMapper()) { api.setItemFinished(itemId, isFinished) }

    override suspend fun updateItem(item: ListItemLocalDto): ApiResult<Unit> =
        apiCall(noMapper()) { api.updateItem(itemId = item.id, item.toCreateListItem()) }
}

private fun ListItemLocalDto.toCreateListItem(): CreateListItemDto = with(this) {
    CreateListItemDto(name, qty, sortNo, unit, category, finished)
}