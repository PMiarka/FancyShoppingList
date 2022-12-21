package com.fansymasters.shoppinglist.list.domain

import kotlinx.coroutines.flow.SharedFlow

internal interface ListsLocalStorageReader {
    val state: SharedFlow<List<ListLocalDto>>
}