package com.fansymasters.shoppinglist.list.createitem.data

import com.fansymasters.shoppinglist.common.noMapper
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.Category
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CreateListItemRepositoryImpl @Inject constructor(
    private val api: ListsApi,
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
    ) {
        apiCall(noMapper()) { api.createItem(listId, createItem(name, unit, quantity, category)) }
    }
}