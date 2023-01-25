package com.fansymasters.shoppinglist.list.createitem.data

import android.util.Log
import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.common.noMapper
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.Category
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
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
) : CreateListItemRepository {
    private fun createItem(name: String, unit: String, quantity: Int, category: Category) =
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
        val item = apiCall(mapper::map) {
            api.createItem(listId, createItem(name, unit, quantity, category))
        }
        Log.e("Piotrek", "CreateListItem also")
        localStorageWriter.addCreatedItem(item)
    }
}