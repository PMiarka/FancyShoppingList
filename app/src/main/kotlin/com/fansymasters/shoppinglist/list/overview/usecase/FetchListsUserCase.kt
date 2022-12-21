package com.fansymasters.shoppinglist.list.overview.usecase

import com.fansymasters.shoppinglist.list.domain.ListsLocalStorageReader
import com.fansymasters.shoppinglist.list.domain.ListsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class FetchListsUserCase @Inject constructor(
    private val repository: ListsRepository,
    listLocalStorageReader: ListsLocalStorageReader
) : FetchListsActions {
    override val state = listLocalStorageReader.state

    override suspend fun fetchLists() {
        repository.fetchLists()
    }
}