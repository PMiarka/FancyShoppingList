package com.fansymasters.shoppinglist.list.createitem.data

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.ApiResult
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.lists.di.Category
import com.fansymasters.shoppinglist.data.onSuccessSuspend
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemsLocalDao
import com.fansymasters.shoppinglist.data.toUnit
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CreateListItemRepositoryImpl @Inject constructor(
    private val api: ListsApi,
    private val listItemsLocalDao: ListItemsLocalDao,
    private val mapper: Mapper<ListItemDto, ListItemLocalDto>
) : CreateListItemRepository {
    fun createItem(name: String, unit: String, quantity: Int, category: Category) =
        CreateListItemDto(
            name = name,
            qty = quantity,
            sortNo = 200,
            unit = unit,
            category = category.apiKey,
            finished = false
        )

    override suspend fun createListItem(
        listId: Int,
        name: String,
        unit: String,
        quantity: Int,
        category: Category,
    ): ApiResult<Unit> =
        apiCall { api.createItem(listId, createItem(name, unit, quantity, category)) }
            .onSuccessSuspend { listItemsLocalDao.insertAll(mapper.map(it)) }
            .toUnit()

}