package com.fansymasters.shoppinglist.list.overview.usecase

import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto
import kotlinx.coroutines.flow.SharedFlow

internal interface FetchListsActions {
    val state: SharedFlow<List<ListLocalDto>>
    suspend fun fetchLists()
}
