package com.fansymasters.shoppinglist.list.domain.lists

import kotlinx.coroutines.flow.SharedFlow

internal interface ListsLocalStorageReader {
    val state: SharedFlow<List<ListLocalDto>>
}