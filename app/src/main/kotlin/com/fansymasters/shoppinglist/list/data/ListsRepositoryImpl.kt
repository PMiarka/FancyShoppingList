package com.fansymasters.shoppinglist.list.data

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.list.domain.ListLocalDto
import com.fansymasters.shoppinglist.list.domain.ListsRepository
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
}