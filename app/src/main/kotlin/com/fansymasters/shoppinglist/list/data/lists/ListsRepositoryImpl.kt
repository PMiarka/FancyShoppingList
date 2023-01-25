package com.fansymasters.shoppinglist.list.data.lists

import android.util.Log
import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.common.noMapper
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListDto
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto
import com.fansymasters.shoppinglist.list.domain.lists.ListsRepository
import javax.inject.Inject

internal class ListsRepositoryImpl @Inject constructor(
    private val api: ListsApi,
    private val listsLocalStorageWriter: ListsLocalStorageWriter,
    private val mapper: Mapper<ListDto, ListLocalDto>
) : ListsRepository {
    override suspend fun fetchLists() {
        val lists: List<ListLocalDto> = apiCall(mapper = { it.map(mapper::map) }) {
            api.fetchUserLists()
        }
        listsLocalStorageWriter.updateLists(lists)
    }

    override suspend fun createList(name: String, description: String) {
        apiCall(mapper::map) {
            api.createList(
                CreateListDto(name = name, description = description)
            )
        }.also {
            Log.e("Pioterk", "CreateList success")
            listsLocalStorageWriter.addNewlyCreateList(it)
        }
    }
}